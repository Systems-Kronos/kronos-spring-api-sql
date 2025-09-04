package com.kronosapisql.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "setor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Setor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nCdSetor")
    private long id;

    @Column(name = "cNmSetor")
    @NotNull
    @Schema(description = "Nome do setor", example = "Limpeza")
    private String nome;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nCdEmpresa", nullable = false)
    private Empresa empresa;
}
