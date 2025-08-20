package com.kronosapisql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "planoVantagens")
public class PlanoVantagens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nCdVantagem")
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nCdPlano", nullable = false)
    private PlanoPagamento planoPagamento;

    @Column(name = "cNmVantagem")
    @NotNull
    private String nomeVantagem;

    @Column(name = "cDescricao")
    @NotNull
    private String descricao;

    public PlanoVantagens(long id, PlanoPagamento planoPagamento, String nomeVantagem, String descricao) {
        this.id = id;
        this.planoPagamento = planoPagamento;
        this.nomeVantagem = nomeVantagem;
        this.descricao = descricao;
    }

    public PlanoVantagens(PlanoPagamento planoPagamento, String nomeVantagem, String descricao) {
        this.planoPagamento = planoPagamento;
        this.nomeVantagem = nomeVantagem;
        this.descricao = descricao;
    }

    public PlanoVantagens() {}

    public long getId() {
        return id;
    }

    public PlanoPagamento getPlanoPagamento() {
        return planoPagamento;
    }

    public String getNomeVantagem() {
        return nomeVantagem;
    }

    public String getDescricao() {
        return descricao;
    }
}
