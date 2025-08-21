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

    @Column(name = "cTitulo")
    @NotNull
    private String titulo;

    @Column(name = "cMensagem")
    @NotNull
    private String mensagem;

    @Column(name = "cCategoria")
    @NotNull
    private String categoria;

}
