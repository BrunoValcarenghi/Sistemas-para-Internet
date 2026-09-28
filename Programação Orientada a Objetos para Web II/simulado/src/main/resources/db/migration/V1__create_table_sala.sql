CREATE TABLE sala (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    codigo VARCHAR(10) NOT NULL UNIQUE,
    capacidade_alunos INT NOT NULL,
    quantidade_computadores INT DEFAULT 0,
    ano_construcao INT NOT NULL,
    area NUMERIC(10, 2) NOT NULL,
    situacao VARCHAR(20) NOT NULL
);