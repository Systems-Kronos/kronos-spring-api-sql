package com.kronosapisql.model;

import jakarta.persistence.*;
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
    @Column(name = "nCdHabilidade")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nCdUsuario", referencedColumnName = "nCdUsuario")
    private Usuario idUsuario;
}