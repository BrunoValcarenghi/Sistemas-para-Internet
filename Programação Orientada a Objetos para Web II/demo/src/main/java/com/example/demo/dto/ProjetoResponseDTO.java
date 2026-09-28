package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(description = "Dados de retorno do Projeto")
public record ProjetoResponseDTO(
        @Schema(description = "Identificador público UUID do projeto", example = "c9bf9e57-1685-4c89-bafb-ff5af830be8a")
        UUID uuid,

        @Schema(description = "Título do projeto académico", example = "Desenvolvimento de Sistema Web com Spring Boot")
        String titulo,

        @Schema(description = "Descrição detalhada do projeto", example = "gestão de trabalhos.")
        String descricao,

        @Schema(description = "Dados resumidos do aluno autor")
        AlunoResponseDTO aluno,

        @Schema(description = "Dados resumidos do avaliador responsável")
        AvaliadorResponseDTO avaliador
) {}