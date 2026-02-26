-- =============================================================
-- SCRIPT DE VALIDAÇÃO DE PERFORMANCE — EXPLAIN ANALYZE
-- Issue: #17 - BD - Validação de Performance e Script de Migração
--
-- Como executar:
--   1. Suba o banco: docker-compose up db -d
--   2. Conecte via psql ou DBeaver no banco 'lanchonete'
--   3. Execute este script e analise os planos de execução
--
-- O que observar nos resultados:
--   - "Seq Scan" em tabelas grandes → candidato a índice
--   - "Hash Join" / "Nested Loop" → método de join escolhido pelo planner
--   - "cost=X..Y" → estimativa de custo (menor = melhor)
--   - "actual time=X..Y" → tempo real em ms
--   - "rows=N" → número de linhas retornadas
-- =============================================================

-- -------------------------------------------------------------
-- 1. vw_pedido_completo
--    Esperado: Hash Joins nas 4 tabelas envolvidas.
--    Se "Seq Scan" aparecer em Pedido/ItemPedido com muitas rows,
--    considerar índice em ItemPedido(id_pedido) e Pagamento(id_pedido).
-- -------------------------------------------------------------
EXPLAIN ANALYZE
SELECT * FROM vw_pedido_completo;

-- Consulta filtrada por situação (caso de uso mais comum):
EXPLAIN ANALYZE
SELECT * FROM vw_pedido_completo
WHERE situacao = 'Entregue';

-- Consulta filtrada por cliente:
EXPLAIN ANALYZE
SELECT * FROM vw_pedido_completo
WHERE cpf_cliente = '11111111111';

-- -------------------------------------------------------------
-- 2. vw_produtos_mais_vendidos
--    Esperado: HashAggregate para o GROUP BY.
--    O planner pode usar Seq Scan em Cardapio e ItemPedido
--    (aceitável, pois precisa de todos os registros para agregar).
-- -------------------------------------------------------------
EXPLAIN ANALYZE
SELECT * FROM vw_produtos_mais_vendidos;

-- Filtro por categoria (uso no dashboard):
EXPLAIN ANALYZE
SELECT * FROM vw_produtos_mais_vendidos
WHERE categoria = 'Lanche';

-- -------------------------------------------------------------
-- 3. vw_estoque_critico
--    Esperado: Seq Scan em Estoque com Filter.
--    Para tabelas de estoque grandes, índice parcial pode ajudar:
--    CREATE INDEX idx_estoque_critico ON Estoque(qtd_estoque_atual)
--    WHERE qtd_estoque_atual <= qtd_estoque_minimo;
-- -------------------------------------------------------------
EXPLAIN ANALYZE
SELECT * FROM vw_estoque_critico;

-- -------------------------------------------------------------
-- 4. vw_pedidos_em_aberto
--    Esperado: Seq Scan em Pedido com Filter por situacao.
--    Se a fila de pedidos crescer muito, avaliar:
--    CREATE INDEX idx_pedido_situacao ON Pedido(situacao)
--    WHERE situacao IN ('Pendente', 'Em Preparo');
-- -------------------------------------------------------------
EXPLAIN ANALYZE
SELECT * FROM vw_pedidos_em_aberto;

-- =============================================================
-- ÍNDICES RECOMENDADOS (aplicar se EXPLAIN mostrar Seq Scan
-- com custo elevado nas tabelas abaixo)
-- =============================================================

-- Acelera join de ItemPedido → Pedido (usado em vw_pedido_completo
-- e vw_produtos_mais_vendidos)
-- CREATE INDEX IF NOT EXISTS idx_itempedido_pedido
--     ON ItemPedido(id_pedido);

-- Acelera join de Pagamento → Pedido (LEFT JOIN em vw_pedido_completo)
-- CREATE INDEX IF NOT EXISTS idx_pagamento_pedido
--     ON Pagamento(id_pedido);

-- Acelera filtro de fila operacional em vw_pedidos_em_aberto
-- CREATE INDEX IF NOT EXISTS idx_pedido_situacao
--     ON Pedido(situacao)
--     WHERE situacao IN ('Pendente', 'Em Preparo');

-- Acelera filtro de reposição em vw_estoque_critico
-- CREATE INDEX IF NOT EXISTS idx_estoque_critico
--     ON Estoque(qtd_estoque_atual, qtd_estoque_minimo);
