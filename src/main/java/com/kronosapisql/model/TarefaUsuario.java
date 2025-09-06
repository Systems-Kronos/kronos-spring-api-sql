package com.kronosapisql.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tarefausuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefaUsuario {

    @Id
    @Column(name = "nCdTarefaUsuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nCdTarefa", referencedColumnName = "nCdTarefa")
    @JsonBackReference
    private Tarefa tarefa;

    @ManyToOne
    @JoinColumn(name = "nCdUsuarioOriginal", referencedColumnName = "nCdUsuario")
    private Usuario usuarioOriginal;

    @ManyToOne
    @JoinColumn(name = "nCdUsuarioAtuante", referencedColumnName = "nCdUsuario")
    private Usuario usuarioAtuante;
}
