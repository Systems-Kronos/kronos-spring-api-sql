package com.kronosapisql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "habilidade")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
}
