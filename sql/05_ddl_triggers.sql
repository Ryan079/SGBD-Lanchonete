-- registrar historico e alterações de preço do cardápio
CREATE TABLE IF NOT EXISTS log_preco_cardapio (
    id_log SERIAL PRIMARY KEY,
    id_cardapio INT NOT NULL,
    nome_produto VARCHAR(100) NOT NULL,
    preco_antigo DECIMAL(10, 2) NOT NULL,
    preco_novo DECIMAL(10, 2) NOT NULL,
    data_alteracao TIMESTAMP DEFAULT NOW(),
    FOREIGN KEY (id_cardapio) REFERENCES cardapio(id_cardapio) ON DELETE CASCADE
);

CREATE OR REPLACE FUNCTION fn_auditar_preco_cardapio()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.preco <> OLD.preco THEN
        INSERT INTO log_preco_cardapio (id_cardapio, nome_produto, preco_antigo, preco_novo, data_alteracao)
        VALUES (OLD.id_cardapio, OLD.nome, OLD.preco, NEW.preco, NOW());
    END IF;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_auditar_preco
AFTER UPDATE ON cardapio
FOR EACH ROW
EXECUTE FUNCTION fn_auditar_preco_cardapio();

-- impedir redução salarial de funcionários
CREATE OR REPLACE FUNCTION fn_valida_salario()
RETURNS TRIGGER AS $$
BEGIN
    -- se o novo salário for menor que o antigo, bloqueia a operação
    IF NEW.salario < OLD.salario THEN
        RAISE EXCEPTION 'Regra de Negócio Violada: O salário do funcionário não pode ser reduzido.';
    END IF;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_valida_salario
BEFORE UPDATE ON funcionario
FOR EACH ROW
EXECUTE FUNCTION fn_valida_salario();

-- atualização automática da data do estoque. sempre que o estoque for modificado, atualiza a data/hora
CREATE OR REPLACE FUNCTION fn_atualiza_data_estoque()
RETURNS TRIGGER AS $$
BEGIN

    NEW.data_ultima_atualizacao = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_atualiza_data_estoque
BEFORE UPDATE ON estoque
FOR EACH ROW
EXECUTE FUNCTION fn_atualiza_data_estoque();