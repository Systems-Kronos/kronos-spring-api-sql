package com.kronosapisql.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Column(name = "cstatus")
    private String status;

    @NotNull
    @Column(name = "ddataatribuicao")
    private Date dataAtribuicao;

    @Column(name = "ddataconclusao")
    private Date dataConclusao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nCdUsuarioRelator", referencedColumnName = "nCdUsuario")
    private Usuario usuarioRelator;

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

    @OneToMany(mappedBy = "tarefa", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TarefaHabilidade> habilidades = new ArrayList<>();

    @OneToMany(mappedBy = "tarefa", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TarefaUsuario> usuariosResponsaveis= new ArrayList<>();


}