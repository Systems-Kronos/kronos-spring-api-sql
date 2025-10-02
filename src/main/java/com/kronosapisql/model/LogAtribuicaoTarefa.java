package com.kronosapisql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "LogAtribuicaoTarefa", schema = "table_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogAtribuicaoTarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nCdLogAtribuicao")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nCdTarefa", referencedColumnName = "nCdTarefa")
    private Tarefa tarefa;

    @NotNull
    @Column(name = "nCdUsuarioAtuante", nullable = false)
    private Long idUsuarioAtuante;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "dRealocacao")
    private Date dataRealocacao;

    @NotNull
    @Column(name = "cObservacao", length = 300)
    private String observacao;
}