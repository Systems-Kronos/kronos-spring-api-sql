package com.kronosapisql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "planoPagamento")
public class PlanoPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nCdPlano")
    private long id;

    @Column(name = "cNmPlano")
    @NotNull
    private String nomePlano;

    @Column(name = "nPreco")
    @NotNull
    private Double preco;

    public PlanoPagamento(long id, String nomePlano, Double preco) {
        this.id = id;
        this.nomePlano = nomePlano;
        this.preco = preco;
    }

    public PlanoPagamento(String nomePlano, Double preco) {
        this.nomePlano = nomePlano;
        this.preco = preco;
    }

    public PlanoPagamento() {}

    public long getId() {
        return id;
    }

    public String getNomePlano() {
        return nomePlano;
    }

    public Double getPreco() {
        return preco;
    }
}
