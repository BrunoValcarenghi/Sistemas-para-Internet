package com.example.simulado.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "sala")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1")
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres")
    @Schema(example = "Sala 109 Bloco F")
    private String nome;

    @NotBlank(message = "O código é obrigatório")
    @Size(max = 10, message = "O código deve conter no máximo 10 caracteres")
    @Column(unique = true, nullable = false, length = 10)
    @Schema(example = "F-109")
    private String codigo;

    @NotNull(message = "A capacidade de alunos é obrigatória")
    @Min(value = 10, message = "A capacidade mínima é de 10 alunos")
    @Max(value = 200, message = "A capacidade máxima é de 200 alunos")
    @Schema(example = "40")
    private Integer capacidadeAlunos;

    @Min(value = 0, message = "A quantidade de computadores deve ser maior ou igual a 0")
    @Schema(example = "20")
    private Integer quantidadeComputadores;

    @NotNull(message = "O ano de construção é obrigatório")
    @Min(value = 1960, message = "O ano de construção deve ser no mínimo 1960")
    @Max(value = 2026, message = "O ano de construção não pode ultrapassar 2026")
    @Schema(example = "2015")
    private Integer anoConstrucao;

    @NotNull(message = "A área é obrigatória")
    @DecimalMin(value = "0.01", message = "A área deve ser maior que 0 m²")
    @Schema(example = "60.0")
    private BigDecimal area;

    @NotNull(message = "A situação é obrigatória")
    @Enumerated(EnumType.STRING)
    @Schema(example = "DISPONIVEL")
    private Situacao situacao;
}