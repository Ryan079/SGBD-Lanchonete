-- =============================================================
-- SCRIPT DE MIGRAÇÃO — CRIAÇÃO DAS VISÕES (VIEWS)
-- Issue: #17 - BD - Validação de Performance e Script de Migração
-- Parent: #5 - Criação de Visões (Views)
--
-- Pré-requisito: 01_ddl_criacao.sql já executado (tabelas existem)
-- Ordem de execução: 01 → 02 → 03
-- =============================================================

-- View 01 - Simplificação de Relacionamentos (#14)
-- Consolida em uma única consulta todos os dados relevantes de
-- um pedido: cliente, itens, produtos e pagamento.

CREATE OR REPLACE VIEW vw_pedido_completo AS
SELECT
    p.id_pedido,
    p.data_hora,
    p.situacao,
    p.endereco_entrega,
    p.taxa_entrega,
    p.valor_total,
    c.cpf                               AS cpf_cliente,
    c.nome                              AS nome_cliente,
    c.telefone                          AS telefone_cliente,
    ip.id_item_pedido,
    ip.quantidade,
    ip.valor_unitario,
    ca.id_cardapio,
    ca.nome                             AS nome_produto,
    ca.categoria                        AS categoria_produto,
    (ip.quantidade * ip.valor_unitario) AS subtotal_item,
    pg.id_pagamento,
    pg.metodo_pagamento,
    pg.valor_pago,
    pg.datahora_pagamento
FROM pedido p
JOIN cliente      c  ON c.cpf            = p.cpf_cliente
JOIN item_pedido   ip ON ip.id_pedido     = p.id_pedido
JOIN cardapio     ca ON ca.id_cardapio   = ip.id_cardapio
LEFT JOIN pagamento pg ON pg.id_pedido   = p.id_pedido;

-- View 02 - Agregação e Métricas (#15)
-- Ranking de produtos por quantidade vendida e receita gerada.
-- Exige GROUP BY + SUM + JOIN — simplificado em uma consulta direta.

CREATE OR REPLACE VIEW vw_produtos_mais_vendidos AS
SELECT
    ca.id_cardapio,
    ca.nome                                    AS nome_produto,
    ca.categoria,
    ca.preco                                   AS preco_unitario,
    SUM(ip.quantidade)                         AS total_unidades_vendidas,
    SUM(ip.quantidade * ip.valor_unitario)     AS receita_total
FROM cardapio ca
JOIN item_pedido ip ON ip.id_cardapio = ca.id_cardapio
JOIN pedido     p  ON p.id_pedido    = ip.id_pedido
WHERE p.situacao != 'Cancelado'
GROUP BY ca.id_cardapio, ca.nome, ca.categoria, ca.preco
ORDER BY total_unidades_vendidas DESC;

-- View 03 - Filtros Estratégicos — Alerta de Reposição (#16)
-- Produtos do estoque cujo saldo atual atingiu ou ficou abaixo
-- do mínimo definido. Encapsula a regra de negócio de reposição
-- para que nenhuma camada precise repeti-la.

CREATE OR REPLACE VIEW vw_estoque_critico AS
SELECT
    id_produto,
    nome,
    unidade_medida,
    qtd_estoque_atual,
    qtd_estoque_minimo,
    (qtd_estoque_minimo - qtd_estoque_atual) AS qtd_a_repor,
    data_ultima_atualizacao
FROM estoque
WHERE qtd_estoque_atual <= qtd_estoque_minimo
ORDER BY qtd_a_repor DESC;

-- View 04 - Filtros Estratégicos — Fila Operacional (#16)
-- Pedidos ainda não finalizados (Pendente ou Em Preparo).
-- Usada pela cozinha/atendimento para acompanhar a fila em
-- tempo real sem precisar filtrar manualmente por situação.

CREATE OR REPLACE VIEW vw_pedidos_em_aberto AS
SELECT
    p.id_pedido,
    p.data_hora,
    p.situacao,
    p.endereco_entrega,
    p.ponto_referencia,
    p.taxa_entrega,
    p.valor_total,
    c.nome      AS nome_cliente,
    c.telefone  AS telefone_cliente
FROM pedido p
JOIN cliente c ON c.cpf = p.cpf_cliente
WHERE p.situacao IN ('Pendente', 'Em Preparo')
ORDER BY p.data_hora ASC;
