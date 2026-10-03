package com.example.simulado2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="sala")
@Schema(description = "entidade que representa sala de aula")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala")
    @Schema(description = "Id unico de cada sala")
    private Integer idSala;

    @NotBlank
    @Size(min = 3, max = 50, message = "Deve conter entre 3 e 50 caracteres")
    @Column(name = "nome")
    @Schema(description = "Nome da sala")
    private String nome;

    @NotNull
    @Min(value=10, message = "Deve caber pelo menos 10alunos")
    @Max(value = 200, message = "Deve caber no maximo 200 alunos")
    @Column(name = "qnt_aluno")
    @Schema(description = "Capacidade de alunos")
    private int qnt_aluno;

    @Min(value = 0, message = "Não pode ser negativo")
    @Column(name = "qnt_pc")
    @Schema(description = "Quantidade de computadores")
    private int qnt_pc;

    @NotNull
    @Min(value = 1960, message = "Deve ser entre 1960 e 2026")
    @Max(value = 2026, message = "Deve ser entre 1960 e 2026")
    @Column(name = "ano")
    @Schema(description = "Ano que a sala foi construida")
    private int ano;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Deve caber entre 10 e 200 alunos")
    @Column(name = "area")
    @Schema(description = "Area")
    private BigDecimal area;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "situacao")
    @Schema(description = "Enum de situacao")
    private Situacao situacao;

}
