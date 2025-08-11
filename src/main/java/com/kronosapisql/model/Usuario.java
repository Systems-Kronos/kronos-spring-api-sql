package com.kronosapisql.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nCdUsuario")
    private long id;

//    @NotNull
//    @OneToOne
//    @JoinColumn(name = "nCdGestor", nullable = false)
//    private Gestor gestor;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "nCdEmpresa", nullable = false)
    private Empresa empresa;
    @NotNull
    @OneToOne
    @JoinColumn(name = "nCdSetor", nullable = false)
    private Setor setor;

    @NotNull
    @Column(name = "nCPF")
    private String cpf;

    @Column(name = "cTelefone")
    private String telefone;

    @NotNull
    @Column(name = "cEmail")
    private String email;
    @NotNull
    @Column(name = "cSenha")
    private String senha;

    @Column(name = "cFoto")
    private String foto;

    public Usuario(long id, Empresa empresa, Setor setor, String cpf, String telefone, String email, String senha, String foto) {
        this.id = id;
//        this.gestor = gestor;
        this.empresa = empresa;
        this.setor = setor;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
    }
    public Usuario(Empresa empresa, Setor setor, String cpf, String telefone, String email, String senha, String foto) {
//        this.empresa = empresa;
        this.setor = setor;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
    }
    public Usuario() {}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public Gestor getGestor() {
//        return gestor;
//    }

//    public void setGestor(Gestor gestor) {
//        this.gestor = gestor;
//    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
