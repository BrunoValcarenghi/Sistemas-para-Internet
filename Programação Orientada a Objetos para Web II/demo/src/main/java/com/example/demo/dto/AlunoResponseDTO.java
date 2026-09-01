package com.example.demo.dto;

import java.util.UUID;

public record AlunoResponseDTO(
        UUID uuid,
        String nome,
        String matricula,
        String email
) {}