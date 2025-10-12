package com.kronosapisql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HabilidadeUsuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabilidadeUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nCdHabilidadeUsuario")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nCdUsuario", referencedColumnName = "nCdUsuario")
    private Usuario usuario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nCdHabilidade", referencedColumnName = "nCdHabilidade")
    private Habilidade habilidade;
}