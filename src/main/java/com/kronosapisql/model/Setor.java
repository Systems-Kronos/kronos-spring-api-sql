package com.kronosapisql.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "setor")
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

    public Setor(long id, String nome, Empresa empresa) {
        this.id = id;
        this.nome = nome;
        this.empresa = empresa;
    }

    public Setor(Empresa empresa, String nome) {
        this.empresa = empresa;
        this.nome = nome;
    }

    public Setor() {}

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Empresa getEmpresa() {
        return empresa;
    }
}
