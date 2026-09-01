CREATE TABLE avaliador (
                             id SERIAL NOT NULL PRIMARY KEY,
                             uuid UUID DEFAULT gen_random_uuid(),
                             nome VARCHAR(100) NOT NULL,
                             especialidade VARCHAR(100) NOT NULL
);