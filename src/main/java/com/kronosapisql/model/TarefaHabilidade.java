package com.kronosapisql.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "tarefahabilidade")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefaHabilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nCdTarefaHabilidade")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nCdTarefa", referencedColumnName = "nCdTarefa")
    @JsonBackReference
    private Tarefa tarefa;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nCdHabilidade", referencedColumnName = "nCdHabilidade")
    private Habilidade habilidade;

    @NotNull
    @Column(name = "iPrioridade")
    private Integer prioridade;
}
