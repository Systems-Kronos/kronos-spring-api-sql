package com.kronosapisql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "report")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
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
    @Column(name = "cDescricao")
    private String descricao;

    @NotNull
    @Column(name = "cProblema")
    private String problema;

    @NotNull
    @Convert(converter = OpcaoStatusConverter.class)
    @Column(name = "cStatus", nullable = false, columnDefinition = "opcao_status")
    private OpcaoStatus status;
}
