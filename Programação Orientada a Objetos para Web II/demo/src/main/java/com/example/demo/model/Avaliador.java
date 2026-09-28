package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "avaliador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa um Avaliador/Professor no sistema")
public class Avaliador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único gerado automaticamente pela base de dados", example = "1")
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    @Schema(description = "Identificador público UUID do avaliador", example = "f47ac10b-58cc-4372-a567-0e02b2c3d4e5")
    private UUID uuid;

    @Column(nullable = false, length = 100)
    @Schema(description = "Nome completo do avaliador", example = "Alencar silva da silva")
    private String nome;

    @Column(nullable = false, length = 100)
    @Schema(description = "Área de especialidade do avaliador", example = "POO Web")
    private String especialidade;

    @PrePersist
    public void prePersist() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
    }
}