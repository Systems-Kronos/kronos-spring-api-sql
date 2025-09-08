package com.kronosapisql.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "LogAtribuicaoTarefa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogAtribuicaoTarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nCdLogAtr")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nCdTarefa", referencedColumnName = "nCdTarefa")
    private Tarefa idTarefa;

    @Column(name = "nCdUsuario", nullable = false)
    private Long idUsuarioAtribuido;

    @Temporal(TemporalType.DATE)
    @Column(name = "dRealocacao")
    private Date dataRealocacao;

    @Column(name = "cObservacao", length = 300)
    private String observacao;

}