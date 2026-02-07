-- 1. Inserindo CLIENTES
INSERT INTO Cliente (cpf, nome, email, telefone, endereco) VALUES
('11111111111', 'João da Silva', 'joao@email.com', '87999991111', 'Rua das Flores, 123, Centro'),
('22222222222', 'Maria Oliveira', 'maria@email.com', '87988882222', 'Av. Brasil, 500, Bairro Novo');

-- 2. Inserindo FUNCIONÁRIOS
INSERT INTO Funcionario (cpf, nome, cargo, data_admissao, salario, cpf_gerente) VALUES
('33333333333', 'Carlos Gerente', 'Gerente', '2024-01-10', 3500.00, NULL);

INSERT INTO Funcionario (cpf, nome, cargo, data_admissao, salario, cpf_gerente) VALUES
('44444444444', 'Ana Atendente', 'Atendente', '2024-02-15', 1500.00, '33333333333');

-- 3. Inserindo FORNECEDORES
INSERT INTO Fornecedor (cnpj, nome_empresa, data_cadastro, telefone) VALUES
('12345678000199', 'Distribuidora Alimentos SA', '2024-01-05', '8733334444'),
('98765432000155', 'Bebidas Garanhuns LTDA', '2024-01-08', '8733335555');

-- 4. Inserindo ESTOQUE (Ingredientes)
INSERT INTO Estoque (nome, unidade_medida, qtd_estoque_atual, qtd_estoque_minimo) VALUES
('Pão de Hambúrguer', 'un', 100, 20),      -- id_produto será 1
('Carne Moída', 'kg', 10, 2),              -- id_produto será 2
('Queijo Mussarela', 'kg', 5, 1),          -- id_produto será 3
('Refrigerante Lata', 'un', 50, 10);       -- id_produto será 4

-- 5. Inserindo CARDÁPIO (Produtos para venda)
INSERT INTO Cardapio (nome, categoria, descricao, preco) VALUES
('X-Bacon', 'Lanche', 'Pão, carne, queijo e bacon', 25.00), -- id_cardapio será 1
('X-Salada', 'Lanche', 'Pão, carne, queijo e salada', 20.00),-- id_cardapio será 2
('Coca-Cola Lata', 'Bebida', 'Lata 350ml', 6.00);            -- id_cardapio será 3

-- 6. Simulando uma COMPRA (Entrada de Estoque)
INSERT INTO Compra (cnpj_fornecedor, valor_total_compra) VALUES
('12345678000199', 150.00); -- id_compra será 1

-- Detalhando o que veio nessa compra (50 Pães e 5kg de Carne)
INSERT INTO ItemCompra (id_compra, id_produto, quantidade, valor_unitario, lote) VALUES
(1, 1, 50, 1.00, 'LOTE_PAO_01'),   -- Comprou Pão (id_produto 1)
(1, 2, 5, 20.00, 'LOTE_CARNE_01'); -- Comprou Carne (id_produto 2)

-- 7. Simulando um PEDIDO (Venda)
-- Cliente João pediu Delivery
INSERT INTO Pedido (situacao, endereco_entrega, ponto_referencia, troco_para, taxa_entrega, valor_total, cpf_cliente) 
VALUES ('Entregue', 'Rua das Flores, 123', 'Casa Azul', 50.00, 5.00, 36.00, '11111111111');

-- 8. Detalhando os itens do Pedido 1
-- Ele pediu 1 X-Bacon e 1 Coca-Cola
INSERT INTO ItemPedido (id_pedido, id_cardapio, quantidade, valor_unitario) VALUES
(1, 1, 1, 25.00), -- X-Bacon (R$ 25,00)
(1, 3, 1, 6.00);  -- Coca-Cola (R$ 6,00)

-- 9. Registrando o PAGAMENTO
-- Total: 25 (Lanche) + 6 (Refri) + 5 (Entrega) = R$ 36,00
INSERT INTO Pagamento (id_pedido, valor_pago, metodo_pagamento) VALUES
(1, 36.00, 'Dinheiro');