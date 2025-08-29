package com.kronosapisql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "planoVantagens")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
}