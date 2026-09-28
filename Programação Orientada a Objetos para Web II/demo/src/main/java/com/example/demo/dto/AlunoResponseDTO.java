package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(description = "Dados de retorno do Aluno")
public record AlunoResponseDTO(
        @Schema(description = "Identificador público UUID do aluno", example = "a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d")
        UUID uuid,

        @Schema(description = "Nome completo do aluno", example = "Bruno Machado")
        String nome,

        @Schema(description = "Número da matricula do aluno", example = "202411968")
        String matricula,

        @Schema(description = "Endereço de email do aluno", example = "aha@gmail.com")
        String email
) {}