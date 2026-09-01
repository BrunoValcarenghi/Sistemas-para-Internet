CREATE TABLE aluno(
                        id SERIAL NOT NULL PRIMARY KEY,
                        uuid UUID DEFAULT gen_random_uuid(),
                        nome VARCHAR(100) NOT NULL,
                        matricula VARCHAR(20) NOT NULL UNIQUE,
                        email VARCHAR(100) NOT NULL UNIQUE
);