# Especificação Técnica das Visões (Views) — Issue #13

## Análise do Modelo Relacional

O modelo relacional da lanchonete é composto por **10 tabelas**:
`Cliente`, `Funcionario`, `Fornecedor`, `Estoque`, `Cardapio`,
`Compra`, `ItemCompra`, `Pedido`, `ItemPedido`, `Pagamento`.

As consultas mais frequentes e complexas do sistema foram identificadas abaixo,
justificando a criação de cada visão.

---

## Visão 01 — `vw_pedido_completo`

### Objetivo de Negócio
Fornecer uma visão consolidada de toda a jornada de um pedido — quem pediu,
o que pediu, quanto custou e como pagou — em uma única consulta. Usada para
relatórios de vendas, histórico do cliente e detalhamento de pedidos no painel
administrativo.

### Complexidade Identificada
Sem a view, qualquer consulta sobre um pedido exige **4 JOINs obrigatórios**:

```sql
-- Query complexa que a view substitui:
SELECT p.id_pedido, p.situacao,
       c.nome AS cliente,
       ip.quantidade, ip.valor_unitario,
       ca.nome AS produto,
       pg.metodo_pagamento, pg.valor_pago
FROM Pedido p
JOIN Cliente      c  ON c.cpf          = p.cpf_cliente      -- relação Pedido → Cliente
JOIN ItemPedido   ip ON ip.id_pedido   = p.id_pedido         -- relação Pedido → Itens
JOIN Cardapio     ca ON ca.id_cardapio = ip.id_cardapio      -- relação Itens → Produto
LEFT JOIN Pagamento pg ON pg.id_pedido = p.id_pedido;        -- relação Pedido → Pagamento
```

### Colunas Expostas
| Coluna | Origem | Descrição |
|---|---|---|
| `id_pedido` | Pedido | Identificador do pedido |
| `situacao` | Pedido | Status atual (Pendente, Em Preparo, Entregue, Cancelado) |
| `nome_cliente` | Cliente | Nome do cliente que realizou o pedido |
| `nome_produto` | Cardapio | Nome do item do cardápio pedido |
| `categoria_produto` | Cardapio | Categoria do item (Lanche, Bebida, Sobremesa) |
| `subtotal_item` | Calculado | `quantidade × valor_unitario` por item |
| `metodo_pagamento` | Pagamento | Forma de pagamento utilizada |
| `valor_pago` | Pagamento | Valor efetivamente pago |

---

## Visão 02 — `vw_produtos_mais_vendidos`

### Objetivo de Negócio
Gerar ranking de desempenho dos produtos do cardápio, mostrando quantas unidades
foram vendidas e quanto cada produto gerou de receita. Usada no dashboard gerencial
para decisões de cardápio, promoções e gestão de estoque.

### Complexidade Identificada
Exige **2 JOINs + GROUP BY + funções de agregação (SUM)** com filtro de negócio
(excluir pedidos cancelados):

```sql
-- Query complexa que a view substitui:
SELECT ca.nome, ca.categoria,
       SUM(ip.quantidade)                    AS total_unidades_vendidas,
       SUM(ip.quantidade * ip.valor_unitario) AS receita_total
FROM Cardapio ca
JOIN ItemPedido ip ON ip.id_cardapio = ca.id_cardapio
JOIN Pedido     p  ON p.id_pedido    = ip.id_pedido
WHERE p.situacao != 'Cancelado'           -- regra de negócio encapsulada
GROUP BY ca.id_cardapio, ca.nome, ca.categoria, ca.preco
ORDER BY total_unidades_vendidas DESC;
```

### Colunas Expostas
| Coluna | Origem | Descrição |
|---|---|---|
| `nome_produto` | Cardapio | Nome do item |
| `categoria` | Cardapio | Categoria do item |
| `preco_unitario` | Cardapio | Preço de venda atual |
| `total_unidades_vendidas` | Calculado (SUM) | Total de unidades vendidas (excl. cancelados) |
| `receita_total` | Calculado (SUM) | Receita bruta gerada pelo produto |

---

## Visão 03 — `vw_estoque_critico`

### Objetivo de Negócio
Identificar imediatamente quais ingredientes precisam de reposição, comparando
o estoque atual com o mínimo definido para cada produto. Usada pelo responsável
de compras para gerar ordens de reposição e acionar fornecedores.

### Complexidade Identificada
Embora consulte uma única tabela, **encapsula uma regra de negócio crítica**
(`atual <= minimo`) e um cálculo derivado (`qtd_a_repor`). Sem a view, a regra
precisaria ser replicada em toda camada que precisasse desse dado:

```sql
-- Query com regra de negócio encapsulada:
SELECT nome, unidade_medida,
       qtd_estoque_atual,
       qtd_estoque_minimo,
       (qtd_estoque_minimo - qtd_estoque_atual) AS qtd_a_repor  -- cálculo derivado
FROM Estoque
WHERE qtd_estoque_atual <= qtd_estoque_minimo                   -- regra de reposição
ORDER BY qtd_a_repor DESC;
```

### Colunas Expostas
| Coluna | Origem | Descrição |
|---|---|---|
| `nome` | Estoque | Nome do ingrediente |
| `unidade_medida` | Estoque | Unidade (kg, un, L...) |
| `qtd_estoque_atual` | Estoque | Quantidade disponível |
| `qtd_estoque_minimo` | Estoque | Limite mínimo configurado |
| `qtd_a_repor` | Calculado | Quantidade necessária para atingir o mínimo |
| `data_ultima_atualizacao` | Estoque | Última movimentação registrada |

---

## Visão 04 — `vw_pedidos_em_aberto` *(bônus)*

### Objetivo de Negócio
Exibir em tempo real a fila de pedidos que ainda precisam ser atendidos
(status `Pendente` ou `Em Preparo`). Usada pela equipe de cozinha e atendimento
para gerenciar a fila de produção e entrega.

### Complexidade Identificada
Requer **JOIN com Cliente** e **filtro composto por múltiplos status**, com
ordenação cronológica para priorizar os pedidos mais antigos:

```sql
-- Query de fila operacional que a view substitui:
SELECT p.id_pedido, p.data_hora, p.situacao,
       p.endereco_entrega, p.ponto_referencia,
       c.nome AS nome_cliente, c.telefone
FROM Pedido p
JOIN Cliente c ON c.cpf = p.cpf_cliente
WHERE p.situacao IN ('Pendente', 'Em Preparo')   -- filtro composto de status
ORDER BY p.data_hora ASC;                         -- mais antigo primeiro (FIFO)
```

### Colunas Expostas
| Coluna | Origem | Descrição |
|---|---|---|
| `id_pedido` | Pedido | Identificador do pedido |
| `data_hora` | Pedido | Momento do pedido (usado para ordenar a fila) |
| `situacao` | Pedido | Status atual |
| `endereco_entrega` | Pedido | Endereço para delivery (nulo se balcão) |
| `nome_cliente` | Cliente | Nome para identificação |
| `telefone_cliente` | Cliente | Contato para retorno |

---

## Resumo Comparativo

| View | Tabelas envolvidas | Recursos SQL utilizados |
|---|---|---|
| `vw_pedido_completo` | 5 (Pedido, Cliente, ItemPedido, Cardapio, Pagamento) | 3 INNER JOINs + 1 LEFT JOIN + coluna calculada |
| `vw_produtos_mais_vendidos` | 3 (Cardapio, ItemPedido, Pedido) | 2 JOINs + GROUP BY + SUM + WHERE com regra de negócio |
| `vw_estoque_critico` | 1 (Estoque) | WHERE com regra de negócio + coluna calculada |
| `vw_pedidos_em_aberto` | 2 (Pedido, Cliente) | JOIN + WHERE composto + ORDER BY |
