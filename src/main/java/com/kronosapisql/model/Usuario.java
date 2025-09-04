package com.kronosapisql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nCdUsuario")
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nCdGestor", nullable = false)
    private Usuario gestor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nCdEmpresa", nullable = false)
    private Empresa empresa;

    @NotNull
    @OneToOne
    @JoinColumn(name = "nCdSetor", nullable = false)
    private Setor setor;

    @NotNull
    @Column(name = "cNmUsuario")
    private String nome;

    @NotNull
    @Column(name = "bGestor")
    private Boolean booleanGestor;

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

    @NotNull
    @Column(name = "bAtivo")
    private Boolean ativo;
}
