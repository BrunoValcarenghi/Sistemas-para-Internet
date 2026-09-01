package com.example.demo.dto;

import java.util.UUID;

import java.util.UUID;

public record ProjetoResponseDTO(
        UUID uuid,
        String titulo,
        String descricao,
        AlunoResponseDTO aluno,
        AvaliadorResponseDTO avaliador
) {}