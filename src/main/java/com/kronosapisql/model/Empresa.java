package com.kronosapisql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nCdEmpresa")
    private Long id;

    @NotNull
    @Column(name = "cNmEmpresa")
    private String nome;

    @NotNull
    @Column(name = "cCNPJ")
    private String cnpj;

    @NotNull
    @Column(name = "cEmail")
    private String email;

    @NotNull
    @Column(name = "cTelefone")
    private String telefone;

    @NotNull
    @Column(name = "cCEP")
    private String cep;

    public Empresa(Long id, String nome, String cnpj, String email, String telefone, String cep) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.telefone = telefone;
        this.cep = cep;
    }

    public Empresa(String nome, String cnpj, String email, String telefone, String cep) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.telefone = telefone;
        this.cep = cep;
    }

    public Empresa() {}

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }
}
