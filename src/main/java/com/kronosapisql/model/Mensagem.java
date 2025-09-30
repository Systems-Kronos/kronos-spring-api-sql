package com.kronosapisql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mensagem")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nCdMensagem")
    private long id;

    @NotNull
    @Column(name = "cTitulo")
    private String titulo;

    @NotNull
    @Column(name = "cMensagem")
    private String mensagem;

    @NotNull
    @Column(name = "cCategoria")
    private String categoria;
}
