package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(description = "Dados de retorno do Avaliador")
public record AvaliadorResponseDTO(
        @Schema(description = "Identificador público UUID do avaliador", example = "f47ac10b-58cc-4372-a567-0e02b2c3d4e5")
        UUID uuid,

        @Schema(description = "Nome completo do avaliador", example = "Alencar dos santos silva da silva")
        String nome,

        @Schema(description = "Área de especialidade do avaliador", example = "POOWEB")
        String especialidade
) {}