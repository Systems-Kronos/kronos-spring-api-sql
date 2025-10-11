package com.kronosapisql.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kronosapisql.converters.OpcaoStatusConverter;
import com.kronosapisql.enums.OpcaoStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnTransformer;

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
    @ManyToOne
    @JoinColumn(name = "nCdUsuarioRelator", referencedColumnName = "nCdUsuario")
    private Usuario usuarioRelator;

    @NotNull
    @OneToMany(mappedBy = "tarefa", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TarefaUsuario> usuariosResponsaveis= new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "tarefa", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TarefaHabilidade> habilidades = new ArrayList<>();

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
    @Column(name = "iTempoEstimado")
    private Integer tempoEstimado;

    @NotNull
    @Column(name = "cDescricao")
    private String descricao;

    @NotNull
    @Convert(converter = OpcaoStatusConverter.class)
    @ColumnTransformer(write = "?::opcao_status")
    @Column(name = "cStatus")
    private OpcaoStatus status;

    @NotNull
    @Column(name = "dDataAtribuicao")
    private Date dataAtribuicao;

    @Column(name = "dDataConclusao")
    private Date dataConclusao;
}
