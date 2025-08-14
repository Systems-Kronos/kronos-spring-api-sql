package com.kronosapisql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "mensagem")
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

    public Mensagem(long id, String titulo, String mensagem, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.categoria = categoria;
    }

    public Mensagem(String titulo, String mensagem, String categoria) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.categoria = categoria;
    }

    public Mensagem() {}

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getCategoria() {
        return categoria;
    }
}
