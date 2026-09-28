package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados para criação de um Avaliador")
public record AvaliadorRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        @Schema(description = "Nome completo do avaliador", example = "Alencar dos santos silva da silva")
        String nome,

        @NotBlank(message = "A especialidade é obrigatória")
        @Schema(description = "Área de especialidade do avaliador", example = "poo web ii")
        String especialidade
) {}