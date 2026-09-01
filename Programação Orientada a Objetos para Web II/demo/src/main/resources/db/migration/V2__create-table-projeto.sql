CREATE TABLE projeto (
                          id SERIAL NOT NULL PRIMARY KEY,
                          uuid UUID DEFAULT gen_random_uuid(),
                          titulo VARCHAR(150) NOT NULL,
                          descricao TEXT NOT NULL
);