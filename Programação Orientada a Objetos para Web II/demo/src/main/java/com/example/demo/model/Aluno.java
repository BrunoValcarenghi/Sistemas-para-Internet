package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "aluno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa um Aluno no sistema")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único gerado automaticamente pela base de dados", example = "1")
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    @Schema(description = "Identificador publico UUID do aluno", example = "a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d")
    private UUID uuid;

    @Column(nullable = false, length = 100)
    @Schema(description = "Nome completo do aluno", example = "Bruno Machado")
    private String nome;

    @Column(nullable = false, unique = true, length = 20)
    @Schema(description = "Número da matricula do aluno", example = "202411968")
    private String matricula;

    @Column(nullable = false, unique = true, length = 100)
    @Schema(description = "Endereço de email institucional do aluno", example = "bruno.valcarenghi@acad.ufsm.br")
    private String email;

    @PrePersist
    public void prePersist() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
    }
}