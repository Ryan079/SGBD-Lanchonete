-- 1. Inserindo CLIENTES
INSERT INTO cliente (cpf, nome, email, telefone, endereco) VALUES
('11111111111', 'João da Silva', 'joao@email.com', '87999991111', 'Rua das Flores, 123, Centro'),
('22222222222', 'Maria Oliveira', 'maria@email.com', '87988882222', 'Av. Brasil, 500, Bairro Novo'),
('33333333333', 'Carlos Souza', 'carlos@email.com', '87977773333', 'Praça Central, 10'),
('44444444444', 'Ana Costa', 'ana@email.com', '87966664444', 'Rua Nova, 45'),
('55555555555', 'Pedro Santos', 'pedro@email.com', '87955555555', 'Bairro Alto, 99');

INSERT INTO cliente (cpf, nome, email, telefone, endereco)
SELECT
    LPAD(CAST(10000000000 + i AS VARCHAR), 11, '0'),
    'Cliente Teste ' || i,
    'cliente' || i || '@teste.com',
    '8790000' || LPAD(CAST(i AS VARCHAR), 4, '0'),
    'Endereço Genérico ' || i
FROM generate_series(6, 50) AS i;

-- 2. Inserindo FUNCIONÁRIOS 
INSERT INTO funcionario (cpf, nome, cargo, data_admissao, salario, cpf_gerente) VALUES
('10000000001', 'Carlos Gerente', 'Gerente', '2024-01-10', 3500.00, NULL),
('10000000002', 'Ana Atendente', 'Atendente', '2024-02-15', 1500.00, '10000000001');

INSERT INTO funcionario (cpf, nome, cargo, data_admissao, salario, cpf_gerente)
SELECT
    LPAD(CAST(20000000000 + i AS VARCHAR), 11, '0'),
    'Atendente ' || i,
    'Atendente',
    CURRENT_DATE - (i * interval '1 day'),
    1500.00,
    '10000000001'
FROM generate_series(3, 50) AS i;

-- 3. Inserindo FORNECEDORES
INSERT INTO fornecedor (cnpj, nome_empresa, data_cadastro, telefone) VALUES
('12345678000199', 'Distribuidora Alimentos SA', '2024-01-05', '8733334444'),
('98765432000155', 'Bebidas Garanhuns LTDA', '2024-01-08', '8733335555');

INSERT INTO fornecedor (cnpj, nome_empresa, data_cadastro, telefone)
SELECT
    LPAD(CAST(30000000000000 + i AS VARCHAR), 14, '0'),
    'Fornecedor Genérico ' || i,
    CURRENT_DATE - (i * interval '2 days'),
    '873333' || LPAD(CAST(i AS VARCHAR), 4, '0')
FROM generate_series(3, 50) AS i;

-- 4. Inserindo ESTOQUE 
INSERT INTO estoque (nome, unidade_medida, qtd_estoque_atual, qtd_estoque_minimo, data_ultima_atualizacao) VALUES
('Pão de Hambúrguer', 'un', 100, 20, NOW()),
('Carne Moída', 'kg', 10, 2, NOW()),
('Queijo Mussarela', 'kg', 5, 10, NOW()), -- abaixo do mínimo de propósito para a View testar "Crítico"
('Refrigerante Lata', 'un', 5, 15, NOW()), -- abaixo do mínimo de propósito
('Batata Frita', 'kg', 20, 5, NOW());

INSERT INTO estoque (nome, unidade_medida, qtd_estoque_atual, qtd_estoque_minimo, data_ultima_atualizacao)
SELECT
    'Ingrediente Secundário ' || i,
    'un',
    50 + i,
    10,
    NOW()
FROM generate_series(6, 50) AS i;

-- 5. Inserindo CARDÁPIO 
INSERT INTO cardapio (nome, categoria, descricao, preco) VALUES
('X-Bacon', 'Lanche', 'Pão, carne, queijo e bacon', 25.00),
('X-Salada', 'Lanche', 'Pão, carne, queijo e salada', 20.00),
('Coca-Cola Lata', 'Bebida', 'Lata 350ml', 6.00),
('Pudim', 'Sobremesa', 'Pudim de leite condensado', 10.00),
('Suco de Laranja', 'Bebida', 'Copo 500ml', 8.00);

INSERT INTO cardapio (nome, categoria, descricao, preco)
SELECT
    'Produto Adicional ' || i,
    CASE WHEN i % 2 = 0 THEN 'Lanche' ELSE 'Bebida' END,
    'Descrição padrão ' || i,
    15.00 + i
FROM generate_series(6, 50) AS i;

-- 6. Inserindo COMPRAS 
INSERT INTO compra (cnpj_fornecedor, valor_total_compra, data_compra)
SELECT
    CASE WHEN i % 2 = 0 THEN '12345678000199' ELSE '98765432000155' END,
    100.00 + (i * 10),
    NOW() - (i * interval '1 day')
FROM generate_series(1, 50) AS i;

-- 7. Inserindo ITENS DA COMPRA 
INSERT INTO item_compra (id_compra, id_produto, quantidade, valor_unitario, lote)
SELECT
    i,  -- id_compra
    (i % 5) + 1, -- id_produto 
    10 + i,
    5.00,
    'LOTE_C' || i
FROM generate_series(1, 50) AS i;

-- 8. Inserindo PEDIDOS
INSERT INTO pedido (situacao, endereco_entrega, ponto_referencia, troco_para, taxa_entrega, valor_total, cpf_cliente) VALUES
('Entregue', 'Rua das Flores, 10', 'Casa Azul', 50.00, 5.00, 35.00, '11111111111'),
('Pendente', 'Av Brasil, 20', 'Apto 101', 100.00, 6.00, 42.00, '22222222222'),
('Em Preparo', 'Rua 15 de Novembro, 30', 'Portão Verde', 0.00, 4.00, 25.00, '33333333333'),
('Cancelado', 'Praça Central, 40', 'Loja', 50.00, 5.00, 30.00, '44444444444'),
('Entregue', 'Rua Direita, 50', 'Esquina', 0.00, 7.00, 55.00, '55555555555'),
('Entregue', 'Bairro Alto, 60', 'Muro Branco', 100.00, 5.00, 38.00, '11111111111'),
('Pendente', 'Rua das Árvores, 70', 'Frente ao mercado', 50.00, 6.00, 45.00, '22222222222'),
('Em Preparo', 'Av Sul, 80', 'Casa 2', 0.00, 5.00, 20.00, '33333333333'),
('Cancelado', 'Rua Norte, 90', 'Sem referência', 50.00, 4.00, 28.00, '44444444444'),
('Entregue', 'Rua Leste, 100', 'Prédio', 0.00, 8.00, 60.00, '55555555555'),
('Entregue', 'Av Oeste, 110', 'Casa 5', 50.00, 5.00, 33.00, '11111111111'),
('Pendente', 'Rua Paulista, 120', 'Condomínio', 100.00, 6.00, 48.00, '22222222222'),
('Em Preparo', 'Av Carioca, 130', 'Casa Amarela', 0.00, 4.00, 22.00, '33333333333'),
('Cancelado', 'Rua Mineira, 140', 'Fundos', 50.00, 5.00, 31.00, '44444444444'),
('Entregue', 'Rua Baiana, 150', 'Casa', 0.00, 7.00, 58.00, '55555555555'),
('Entregue', 'Av Pernambuco, 160', 'Frente', 50.00, 5.00, 37.00, '11111111111'),
('Pendente', 'Rua Recife, 170', 'Lado direito', 100.00, 6.00, 40.00, '22222222222'),
('Em Preparo', 'Rua Olinda, 180', 'Casa 1', 0.00, 4.00, 26.00, '33333333333'),
('Cancelado', 'Av Caruaru, 190', 'Apto 202', 50.00, 5.00, 32.00, '44444444444'),
('Entregue', 'Rua Garanhuns, 200', 'Condomínio fechado', 0.00, 7.00, 50.00, '55555555555'),
('Entregue', 'Rua das Margaridas, 210', 'Casa Branca', 50.00, 5.00, 36.00, '11111111111'),
('Pendente', 'Av das Rosas, 220', 'Perto da padaria', 100.00, 6.00, 44.00, '22222222222'),
('Em Preparo', 'Rua dos Cravos, 230', 'Ao lado do posto', 0.00, 4.00, 24.00, '33333333333'),
('Cancelado', 'Av dos Girassóis, 240', 'Casa 3', 50.00, 5.00, 29.00, '44444444444'),
('Entregue', 'Rua das Violetas, 250', 'Fundo', 0.00, 7.00, 52.00, '55555555555'),
('Entregue', 'Rua das Tulipas, 260', 'Sem referência', 50.00, 5.00, 34.00, '11111111111'),
('Pendente', 'Av das Orquídeas, 270', 'Casa', 100.00, 6.00, 41.00, '22222222222'),
('Em Preparo', 'Rua dos Lírios, 280', 'Apto 303', 0.00, 4.00, 27.00, '33333333333'),
('Cancelado', 'Av das Palmas, 290', 'Muro de vidro', 50.00, 5.00, 35.00, '44444444444'),
('Entregue', 'Rua dos Ipês, 300', 'Esquina', 0.00, 7.00, 57.00, '55555555555'),
('Entregue', 'Rua 1, Nova', 'Casa', 50.00, 5.00, 39.00, '11111111111'),
('Pendente', 'Rua 2, Nova', 'Apto 1', 100.00, 6.00, 46.00, '22222222222'),
('Em Preparo', 'Rua 3, Nova', 'Fundo', 0.00, 4.00, 21.00, '33333333333'),
('Cancelado', 'Rua 4, Nova', 'Frente', 50.00, 5.00, 26.00, '44444444444'),
('Entregue', 'Rua 5, Nova', 'Casa Azul', 0.00, 7.00, 51.00, '55555555555'),
('Entregue', 'Av 1, Sul', 'Muro Alto', 50.00, 5.00, 32.00, '11111111111'),
('Pendente', 'Av 2, Sul', 'Portão', 100.00, 6.00, 43.00, '22222222222'),
('Em Preparo', 'Av 3, Sul', 'Rua de terra', 0.00, 4.00, 23.00, '33333333333'),
('Cancelado', 'Av 4, Sul', 'Apto 10', 50.00, 5.00, 28.00, '44444444444'),
('Entregue', 'Av 5, Sul', 'Condomínio', 0.00, 7.00, 54.00, '55555555555'),
('Entregue', 'Travessa 1', 'Casa', 50.00, 5.00, 34.00, '11111111111'),
('Pendente', 'Travessa 2', 'Apto', 100.00, 6.00, 47.00, '22222222222'),
('Em Preparo', 'Travessa 3', 'Esquina', 0.00, 4.00, 29.00, '33333333333'),
('Cancelado', 'Travessa 4', 'Portão', 50.00, 5.00, 30.00, '44444444444'),
('Entregue', 'Travessa 5', 'Loja', 0.00, 7.00, 59.00, '55555555555'),
('Entregue', 'Beco 1', 'Fundo', 50.00, 5.00, 35.00, '11111111111'),
('Pendente', 'Beco 2', 'Frente', 100.00, 6.00, 42.00, '22222222222'),
('Em Preparo', 'Beco 3', 'Muro', 0.00, 4.00, 25.00, '33333333333'),
('Cancelado', 'Beco 4', 'Sem ref', 50.00, 5.00, 31.00, '44444444444'),
('Entregue', 'Beco 5', 'Casa 2', 0.00, 7.00, 56.00, '55555555555');

-- 9. Inserindo ITENS DO PEDIDO 
INSERT INTO item_pedido (id_pedido, id_cardapio, quantidade, valor_unitario)
SELECT
    i, -- id_pedido
    (i % 5) + 1, -- id_cardapio
    (i % 3) + 1, -- quantidade 
    15.00
FROM generate_series(1, 50) AS i;

-- 10. Inserindo PAGAMENTOS 
INSERT INTO pagamento (id_pedido, valor_pago, metodo_pagamento, datahora_pagamento)
SELECT
    i,
    30.00 + i,
    CASE
        WHEN i % 3 = 0 THEN 'Pix'
        WHEN i % 3 = 1 THEN 'Dinheiro'
        ELSE 'Cartão de Crédito'
    END,
    NOW() - (i * interval '1 hour')
FROM generate_series(1, 50) AS i;