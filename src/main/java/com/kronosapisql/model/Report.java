package com.kronosapisql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "report")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nCdReport")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nCdTarefa", nullable = false)
    private Tarefa tarefa;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nCdUsuario", nullable = false)
    private Usuario usuario;

    @NotNull
    @Column(name = "cDescricao")
    private String descricao;

    @NotNull
    @Column(name = "cProblema")
    private String problema;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "cStatus")
    private OpcaoStatus status;
}
