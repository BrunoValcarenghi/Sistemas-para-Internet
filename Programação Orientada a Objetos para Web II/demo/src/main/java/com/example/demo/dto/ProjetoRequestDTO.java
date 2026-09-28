package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Schema(description = "Dados para criação de um Projeto")
public record ProjetoRequestDTO(
        @NotBlank(message = "O título é obrigatório")
        @Schema(description = "Título do projeto académico", example = "Desenvolvimento de Sistema Web com Spring Boot")
        String titulo,

        @NotBlank(message = "A descrição é obrigatória")
        @Schema(description = "Descrição detalhada do projeto", example = "gestão de trabalhos.")
        String descricao,

        @NotNull(message = "O UUID do aluno é obrigatório")
        @Schema(description = "UUID do aluno autor do projeto", example = "a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d")
        UUID alunoUuid,

        @NotNull(message = "O UUID do avaliador é obrigatório")
        @Schema(description = "UUID do avaliador responsável", example = "f47ac10b-58cc-4372-a567-0e02b2c3d4e5")
        UUID avaliadorUuid
) {}