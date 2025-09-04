package com.kronosapisql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "tarefa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarefa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nCdTarefa")
    private Long id;

    @NotNull
    @Column(name = "cNmTarefa")
    private String nome;

    @NotNull
    @Column(name = "cDescricao")
    private String descricao;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "nCdUsuarioResponsavel", referencedColumnName = "nCdUsuario")
    private Usuario usuarioResponsavel;

    @NotNull
    @JoinColumn(name = "nCdHablidade", referencedColumnName = "nCdHabilidade")
    private String habilidade;

    @NotNull
    @Column(name = "iGravidade")
    private Integer gravidade ;

    @NotNull
    @Column(name = "iUrgencia")
    private Integer urgencia;

    @NotNull
    @Column(name = "iTendencia")
    private Integer tendencia;

    @NotNull
    @Column(name = "nTempoEstimado")
    private Double tempoEstimado;
}