-- TABELAS DE CADASTRO
CREATE TABLE Cliente (
    cpf CHAR(11) PRIMARY KEY, 
    nome VARCHAR(100) NOT NULL,
    email TEXT,
    telefone VARCHAR(20),
    endereco TEXT
);

CREATE TABLE Funcionario (
    cpf CHAR(11) PRIMARY KEY,
    nome TEXT NOT NULL,
    cargo TEXT NOT NULL,
    data_admissao DATE NOT NULL,
    salario DECIMAL(10,2) NOT NULL,
    cpf_gerente CHAR(11),
    CONSTRAINT fk_func_gerente FOREIGN KEY (cpf_gerente) REFERENCES Funcionario(cpf)
);

CREATE TABLE Mesa (
    numero_mesa INT PRIMARY KEY, 
    capacidade INT
);

CREATE TABLE Fornecedor (
    cnpj CHAR(14) PRIMARY KEY, 
    nome_empresa TEXT NOT NULL,
    data_cadastro DATE NOT NULL,
    telefone VARCHAR(20)
);

CREATE TABLE Produto (
    id_produto SERIAL PRIMARY KEY,
    nome TEXT NOT NULL,
    categoria VARCHAR(20) CHECK (categoria IN ('Bebida', 'Ingrediente', 'Lanche')),
    descricao TEXT,
    preco_venda DECIMAL(10,2), 
    qtd_estoque_atual INT DEFAULT 0,
    qtd_estoque_minimo INT DEFAULT 5 
);

CREATE TABLE Compra (
    id_compra SERIAL PRIMARY KEY,
    data_compra TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cnpj_fornecedor CHAR(14) NOT NULL, 
    valor_total_compra DECIMAL(10,2) DEFAULT 0,
    
    CONSTRAINT fk_compra_fornecedor FOREIGN KEY (cnpj_fornecedor) REFERENCES Fornecedor(cnpj)
);

CREATE TABLE ItemCompra (
    id_item_compra SERIAL PRIMARY KEY,
    id_compra INT NOT NULL,
    id_produto INT NOT NULL,
    quantidade INT NOT NULL,
    valor_unitario DECIMAL(10,2) NOT NULL,
    lote TEXT, 
    
    CONSTRAINT fk_ic_compra FOREIGN KEY (id_compra) REFERENCES Compra(id_compra),
    CONSTRAINT fk_ic_produto FOREIGN KEY (id_produto) REFERENCES Produto(id_produto)
);

CREATE TABLE Pedido (
    id_pedido SERIAL PRIMARY KEY,
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'Pendente' CHECK (status IN ('Pendente', 'Em Preparo', 'Entregue', 'Cancelado')),
    tipo_pedido VARCHAR(20) NOT NULL CHECK (tipo_pedido IN ('Local', 'Delivery', 'Balcao')),
    
    numero_mesa INT,
    endereco_entrega TEXT,
    ponto_referencia TEXT, 
    troco_para DECIMAL(10,2),
    taxa_entrega DECIMAL(10,2) DEFAULT 0.00,
    
    cpf_cliente CHAR(11),      
    cpf_funcionario CHAR(11) NOT NULL,
    
    CONSTRAINT fk_ped_mesa FOREIGN KEY (numero_mesa) REFERENCES Mesa(numero_mesa),
    CONSTRAINT fk_ped_cli FOREIGN KEY (cpf_cliente) REFERENCES Cliente(cpf),
    CONSTRAINT fk_ped_func FOREIGN KEY (cpf_funcionario) REFERENCES Funcionario(cpf)
);

CREATE TABLE ItemPedido (
    id_item_pedido SERIAL PRIMARY KEY, 
    id_pedido INT NOT NULL,  
    id_produto INT NOT NULL, 
    quantidade INT DEFAULT 1,
    valor_unitario DECIMAL(10,2) NOT NULL, 
    observacao VARCHAR(255), 
    
    CONSTRAINT fk_ip_pedido FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido),
    CONSTRAINT fk_ip_produto FOREIGN KEY (id_produto) REFERENCES Produto(id_produto)
);

CREATE TABLE Pagamento (
    id_pagamento SERIAL PRIMARY KEY,
    id_pedido INT NOT NULL,
    valor_pago DECIMAL(10,2) NOT NULL,
    metodo_pagamento VARCHAR(50) NOT NULL,
    datahora_pagamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_pag_pedido FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido)
);


-- Inserir 


INSERT INTO Fornecedor (cnpj, nome_empresa, data_cadastro, telefone) VALUES ('12345678000199', 'Atacadão Bebidas', '2023-10-01', '1199999999');
INSERT INTO Produto (nome, categoria, preco_venda, qtd_estoque_atual) VALUES ('Coca-Cola', 'Bebida', 6.00, 100);
INSERT INTO Produto (nome, categoria, preco_venda, qtd_estoque_atual) VALUES ('X-Bacon', 'Lanche', 25.00, 0);

INSERT INTO Funcionario (cpf, nome, cargo, data_admissao, salario) VALUES ('11122233344', 'João Gerente', 'Gerente', '2023-01-01', 3000.00);
INSERT INTO Cliente (cpf, nome, telefone) VALUES ('99988877766', 'Maria Cliente', '11988888888');

INSERT INTO Mesa (numero_mesa, capacidade) VALUES (1, 4);

INSERT INTO Pedido (tipo_pedido, numero_mesa, cpf_funcionario, cpf_cliente, status) 
VALUES ('Local', 1, '11122233344', '99988877766', 'Pendente');

INSERT INTO ItemPedido (id_pedido, id_produto, quantidade, valor_unitario) VALUES (1, 1, 1, 6.00);
INSERT INTO ItemPedido (id_pedido, id_produto, quantidade, valor_unitario) VALUES (1, 2, 1, 25.00);