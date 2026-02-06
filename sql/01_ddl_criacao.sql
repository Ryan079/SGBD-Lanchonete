CREATE TABLE Cliente (
    cpf CHAR(11) PRIMARY KEY, 
    nome VARCHAR(100) NOT NULL,
    email TEXT,
    telefone VARCHAR(20),
    endereco TEXT
);

CREATE TABLE Funcionario (
    cpf CHAR(11) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    data_admissao DATE NOT NULL,
    salario DECIMAL(10,2) NOT NULL,
    cpf_gerente CHAR(11),
    
    CONSTRAINT fk_func_gerente FOREIGN KEY (cpf_gerente) REFERENCES Funcionario(cpf)
);

CREATE TABLE Fornecedor (
    cnpj CHAR(14) PRIMARY KEY, 
    nome_empresa VARCHAR(100) NOT NULL,
    data_cadastro DATE NOT NULL,
    telefone VARCHAR(20)
);

CREATE TABLE Estoque (
    id_produto SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    unidade_medida VARCHAR(10) NOT NULL, 
    qtd_estoque_atual INT DEFAULT 0,
    qtd_estoque_minimo INT DEFAULT 5,
    data_ultima_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Cardapio (
    id_cardapio SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(20) CHECK (categoria IN ('Bebida', 'Lanche', 'Sobremesa')),
    descricao TEXT,
    preco DECIMAL(10,2) NOT NULL
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
    lote VARCHAR(50), 
    
    CONSTRAINT fk_ic_compra FOREIGN KEY (id_compra) REFERENCES Compra(id_compra),
    CONSTRAINT fk_ic_produto FOREIGN KEY (id_produto) REFERENCES Estoque(id_produto)
);

CREATE TABLE Pedido (
    id_pedido SERIAL PRIMARY KEY,
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    situacao VARCHAR(20) DEFAULT 'Pendente' CHECK (situacao IN ('Pendente', 'Em Preparo', 'Entregue', 'Cancelado')),
    
    endereco_entrega TEXT,
    ponto_referencia TEXT, 
    troco_para DECIMAL(10,2),
    taxa_entrega DECIMAL(10,2) DEFAULT 0.00,
    
    cpf_cliente CHAR(11),      
    CONSTRAINT fk_ped_cli FOREIGN KEY (cpf_cliente) REFERENCES Cliente(cpf)
);

CREATE TABLE ItemPedido (
    id_item_pedido SERIAL PRIMARY KEY, 
    id_pedido INT NOT NULL,  
    id_cardapio INT NOT NULL,
    quantidade INT DEFAULT 1,
    valor_unitario DECIMAL(10,2) NOT NULL, 
    
    CONSTRAINT fk_ip_pedido FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido),
    CONSTRAINT fk_ip_cardapio FOREIGN KEY (id_cardapio) REFERENCES Cardapio(id_cardapio)
);

CREATE TABLE Pagamento (
    id_pagamento SERIAL PRIMARY KEY,
    id_pedido INT NOT NULL,
    valor_pago DECIMAL(10,2) NOT NULL,
    metodo_pagamento VARCHAR(50) NOT NULL,
    datahora_pagamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_pag_pedido FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido)
);