package com.kronosapisql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "planoPagamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanoPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nCdPlano")
    private long id;

    @NotNull
    @Column(name = "cNmPlano")
    private String nomePlano;

    @NotNull
    @Column(name = "nPreco")
    private Double preco;
}
