ALTER TABLE projeto
    ADD COLUMN aluno_id BIGINT,
ADD COLUMN avaliador_id BIGINT;

ALTER TABLE projeto
    ADD CONSTRAINT fk_projeto_aluno
        FOREIGN KEY (aluno_id) REFERENCES aluno(id) ON DELETE SET NULL;

ALTER TABLE projeto
    ADD CONSTRAINT fk_projeto_avaliador
        FOREIGN KEY (avaliador_id) REFERENCES avaliador(id) ON DELETE SET NULL;