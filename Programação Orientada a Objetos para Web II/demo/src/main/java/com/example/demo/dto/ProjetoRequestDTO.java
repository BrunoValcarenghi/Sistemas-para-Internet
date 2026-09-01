package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record ProjetoRequestDTO(
        @NotBlank(message = "O título é obrigatório")
        String titulo,

        @NotBlank(message = "A descrição é obrigatória")
        String descricao,

        @NotNull(message = "O UUID do aluno é obrigatório")
        UUID alunoUuid,

        @NotNull(message = "O UUID do avaliador é obrigatório")
        UUID avaliadorUuid
) {}