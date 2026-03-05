
-- ===================================================================
-- View 01 - Pedido Completo
-- Propósito: Detalhes completos de UM pedido (consulta detalhada)
-- ===================================================================
CREATE OR REPLACE VIEW vw_pedido_completo AS
SELECT
    ip.id_item_pedido,
    p.id_pedido,
    p.data_hora,
    p.situacao,
    p.endereco_entrega,
    p.taxa_entrega,
    p.valor_total,
    p.cpf_cliente,
    c.nome                              AS nome_cliente,
    c.telefone                          AS telefone_cliente,
    ip.quantidade,
    ip.valor_unitario,
    (ip.quantidade * ip.valor_unitario) AS subtotal_item,
    ca.id_cardapio,
    ca.nome                             AS nome_produto,
    ca.categoria                        AS categoria_produto,
    pg.id_pagamento,
    pg.metodo_pagamento
FROM pedido p
JOIN cliente       c  ON c.cpf            = p.cpf_cliente
JOIN item_pedido   ip ON ip.id_pedido     = p.id_pedido
JOIN cardapio      ca ON ca.id_cardapio   = ip.id_cardapio
LEFT JOIN pagamento pg ON pg.id_pedido    = p.id_pedido;

-- ===================================================================
-- View 02 - Produtos Mais Vendidos
-- Propósito: Ranking de produtos por volume e receita
-- ===================================================================
CREATE OR REPLACE VIEW vw_produtos_mais_vendidos AS
SELECT
    ca.id_cardapio,
    ca.nome AS nome_produto,
    ca.categoria,
    ca.preco AS preco_unitario,
    SUM(ip.quantidade) AS total_unidades_vendidas,
    SUM(ip.quantidade * ip.valor_unitario) AS receita_total,
    ROUND(SUM(ip.quantidade * ip.valor_unitario) / SUM(ip.quantidade), 2) AS preco_medio_realizado
FROM cardapio ca
JOIN item_pedido ip ON ip.id_cardapio = ca.id_cardapio
JOIN pedido p ON p.id_pedido = ip.id_pedido
WHERE p.situacao != 'Cancelado'
GROUP BY ca.id_cardapio, ca.nome, ca.categoria, ca.preco
ORDER BY total_unidades_vendidas DESC;

-- ===================================================================
-- View 03 - Estoque Crítico
-- Propósito: Alerta de produtos que precisam reposição
-- ===================================================================
CREATE OR REPLACE VIEW vw_estoque_critico AS
SELECT
    id_produto,
    nome,
    qtd_estoque_atual,
    qtd_estoque_minimo,
    (qtd_estoque_minimo - qtd_estoque_atual) AS qtd_a_repor
FROM estoque
WHERE qtd_estoque_atual <= qtd_estoque_minimo
ORDER BY qtd_a_repor DESC;

-- ===================================================================
-- View 04 - Pedidos em Aberto
-- Propósito: Fila operacional para cozinha/atendimento
-- ===================================================================
CREATE OR REPLACE VIEW vw_pedidos_em_aberto AS
SELECT
    p.id_pedido,
    p.data_hora,
    p.situacao,
    c.nome AS nome_cliente
FROM pedido p
JOIN cliente c ON c.cpf = p.cpf_cliente
WHERE p.situacao IN ('Pendente', 'Em Preparo')
ORDER BY p.data_hora ASC;



-- ===================================================================
-- View 05 - Faturamento Diário
-- Propósito: Análise financeira com métricas consolidadas
-- ===================================================================
CREATE OR REPLACE VIEW vw_faturamento_diario AS
SELECT 
    DATE(p.data_hora) AS data,
    COUNT(p.id_pedido) AS total_pedidos,
    SUM(p.valor_total) AS faturamento_total,
    AVG(p.valor_total) AS ticket_medio,
    MIN(p.valor_total) AS ticket_minimo,
    MAX(p.valor_total) AS ticket_maximo
FROM pedido p
WHERE p.situacao IN ('Entregue', 'Finalizado')
GROUP BY DATE(p.data_hora)
ORDER BY data DESC;


-- ===================================================================
-- View 06 - Clientes Frequentes
-- Propósito: Análise de clientes com maior frequência e gasto
-- ===================================================================
CREATE OR REPLACE VIEW vw_clientes_frequentes AS
SELECT 
    c.cpf,
    c.nome AS cliente,
    c.telefone,
    COUNT(p.id_pedido) AS total_pedidos,
    SUM(p.valor_total) AS valor_total_gasto,
    AVG(p.valor_total) AS ticket_medio
FROM cliente c
INNER JOIN pedido p ON c.cpf = p.cpf_cliente
WHERE p.situacao IN ('Entregue', 'Finalizado')
GROUP BY c.cpf, c.nome, c.telefone
ORDER BY total_pedidos DESC, valor_total_gasto DESC;


-- ===================================================================
-- View 07 - Horários de Pico
-- Propósito: Análise de demanda por hora (dimensionamento de equipe)
-- ===================================================================
CREATE OR REPLACE VIEW vw_horarios_pico AS
SELECT 
    EXTRACT(HOUR FROM data_hora) AS hora,
    COUNT(*) AS quantidade_pedidos,
    AVG(valor_total) AS ticket_medio,
    SUM(valor_total) AS faturamento_total
FROM pedido
WHERE situacao != 'Cancelado'
GROUP BY EXTRACT(HOUR FROM data_hora)
ORDER BY quantidade_pedidos DESC;


-- ===================================================================
-- View 08 - Análise de Pagamentos
-- Propósito: Distribuição de vendas por método de pagamento
-- ===================================================================
CREATE OR REPLACE VIEW vw_analise_pagamentos AS
SELECT 
    pg.metodo_pagamento,
    COUNT(pg.id_pagamento) AS total_transacoes,
    SUM(pg.valor_pago) AS valor_total,
    AVG(pg.valor_pago) AS ticket_medio
FROM pagamento pg
INNER JOIN pedido p ON pg.id_pedido = p.id_pedido
WHERE p.situacao IN ('Entregue', 'Finalizado')
GROUP BY pg.metodo_pagamento
ORDER BY valor_total DESC;


-- ===================================================================
-- View 09 - Produtos com Baixa Rotatividade
-- Propósito: Identificar produtos de baixo movimento (últimos 30 dias)
-- ===================================================================
CREATE OR REPLACE VIEW vw_produtos_baixa_rotatividade AS
SELECT 
    c.id_cardapio,
    c.nome AS produto,
    c.categoria,
    c.preco,
    COALESCE(SUM(ip.quantidade), 0) AS vendas_ultimos_30_dias,
    COALESCE(SUM(ip.quantidade * ip.valor_unitario), 0) AS receita_ultimos_30_dias
FROM cardapio c
LEFT JOIN item_pedido ip ON c.id_cardapio = ip.id_cardapio
LEFT JOIN pedido p ON ip.id_pedido = p.id_pedido 
    AND p.data_hora >= CURRENT_DATE - INTERVAL '30 days'
    AND p.situacao != 'Cancelado'
GROUP BY c.id_cardapio, c.nome, c.categoria, c.preco
HAVING COALESCE(SUM(ip.quantidade), 0) <= 10
ORDER BY vendas_ultimos_30_dias ASC;


-- ===================================================================
-- View 10 - Dashboard Executivo
-- Propósito: KPIs consolidados para acompanhamento gerencial (tempo real)
-- ===================================================================
CREATE OR REPLACE VIEW vw_dashboard_executivo AS
SELECT 
    CAST(1 AS BIGINT) AS id,
    COUNT(DISTINCT CASE WHEN DATE(p.data_hora) = CURRENT_DATE THEN p.id_pedido END) AS pedidos_hoje,
    COALESCE(SUM(CASE WHEN DATE(p.data_hora) = CURRENT_DATE AND p.situacao IN ('Entregue', 'Finalizado') THEN p.valor_total ELSE 0 END), 0) AS faturamento_hoje,
    COUNT(DISTINCT CASE WHEN e.qtd_estoque_atual <= e.qtd_estoque_minimo THEN e.id_produto END) AS produtos_estoque_critico,
    COUNT(DISTINCT CASE WHEN DATE(p.data_hora) = CURRENT_DATE THEN p.cpf_cliente END) AS clientes_atendidos_hoje,
    COUNT(DISTINCT CASE WHEN p.situacao IN ('Pendente', 'Em Preparo') THEN p.id_pedido END) AS pedidos_em_aberto,
    ROUND(COALESCE(AVG(CASE WHEN DATE_TRUNC('month', p.data_hora) = DATE_TRUNC('month', CURRENT_DATE) AND p.situacao IN ('Entregue', 'Finalizado') THEN p.valor_total END), 0), 2) AS ticket_medio_mes,
    COALESCE(SUM(CASE WHEN DATE_TRUNC('month', p.data_hora) = DATE_TRUNC('month', CURRENT_DATE) AND p.situacao IN ('Entregue', 'Finalizado') THEN p.valor_total ELSE 0 END), 0) AS faturamento_mes
FROM pedido p
LEFT JOIN estoque e ON e.qtd_estoque_atual <= e.qtd_estoque_minimo;


-- ===================================================================
-- View 11 - Ranking de Categorias
-- Propósito: Desempenho de vendas por categoria de produto
-- ===================================================================
CREATE OR REPLACE VIEW vw_ranking_categorias AS
SELECT 
    ca.categoria,
    COUNT(DISTINCT ca.id_cardapio) AS total_produtos,
    SUM(ip.quantidade) AS total_unidades_vendidas,
    SUM(ip.quantidade * ip.valor_unitario) AS receita_total,
    AVG(ip.valor_unitario) AS preco_medio
FROM cardapio ca
INNER JOIN item_pedido ip ON ca.id_cardapio = ip.id_cardapio
INNER JOIN pedido p ON ip.id_pedido = p.id_pedido
WHERE p.situacao IN ('Entregue', 'Finalizado', 'Pendente', 'Em Preparo')
GROUP BY ca.categoria
ORDER BY receita_total DESC;
