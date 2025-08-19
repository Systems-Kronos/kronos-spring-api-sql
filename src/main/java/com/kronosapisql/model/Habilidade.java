package com.kronosapisql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "habilidade")
public class Habilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nCdHabilidade")
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nCdEmpresa")
    private Empresa empresa;

    @NotNull
    @Column(name = "cNmHabilidade")
    private String nome;

    @NotNull
    @Column(name = "cDescricao")
    private String descricao;

    public Habilidade(long id, Empresa empresa, String nome, String descricao) {
        this.id = id;
        this.empresa = empresa;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Habilidade(Empresa empresa, String nome, String descricao) {
        this.empresa = empresa;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Habilidade(){}

    public long getId() {
        return id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
