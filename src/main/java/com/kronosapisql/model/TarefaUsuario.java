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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nCdTarefa", referencedColumnName = "nCdTarefa")
    @JsonBackReference
    private Tarefa tarefa;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nCdUsuarioOriginal", referencedColumnName = "nCdUsuario")
    private Usuario usuarioOriginal;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nCdUsuarioAtuante", referencedColumnName = "nCdUsuario")
    private Usuario usuarioAtuante;
}
