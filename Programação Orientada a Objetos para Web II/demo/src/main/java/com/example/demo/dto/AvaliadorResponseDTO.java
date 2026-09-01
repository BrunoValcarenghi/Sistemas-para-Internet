package com.example.demo.dto;

import java.util.UUID;

public record AvaliadorResponseDTO(
        UUID uuid,
        String nome,
        String especialidade
) {}