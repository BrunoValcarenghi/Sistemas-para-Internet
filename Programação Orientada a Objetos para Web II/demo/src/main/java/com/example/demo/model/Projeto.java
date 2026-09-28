package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "projeto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa um Projeto Académico")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único gerado automaticamente pela base de dados", example = "1")
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    @Schema(description = "Identificador público UUID do projeto", example = "c9bf9e57-1685-4c89-bafb-ff5af830be8a")
    private UUID uuid;

    @Column(nullable = false, length = 150)
    @Schema(description = "Título do projeto academico", example = "Desenvolvimento de Sistema Web com Spring Boot")
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Schema(description = "Descrição detalhada do projeto", example = "gestão de trabalhos.")
    private String descricao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "aluno_id", nullable = false)
    @Schema(description = "Aluno autor do projeto")
    private Aluno aluno;

    @ManyToOne(optional = false)
    @JoinColumn(name = "avaliador_id", nullable = false)
    @Schema(description = "Avaliador responsável pela revisão do projeto")
    private Avaliador avaliador;

    @PrePersist
    public void prePersist() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
    }
}