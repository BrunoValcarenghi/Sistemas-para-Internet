package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados para criação de um Aluno")
public record AlunoRequestDTO(
        @NotBlank(message = "O nome é obrigatorio")
        @Schema(description = "Nome completo do aluno", example = "Bruno Machado")
        String nome,

        @NotBlank(message = "A matricula é obrigatoria")
        @Schema(description = "Número da matricula do aluno", example = "202411968")
        String matricula,

        @NotBlank(message = "O email é obrigatorio")
        @Email(message = "Email invalido")
        @Schema(description = "Endereço de email do aluno", example = "email@acad.usfm.br")
        String email
) {}