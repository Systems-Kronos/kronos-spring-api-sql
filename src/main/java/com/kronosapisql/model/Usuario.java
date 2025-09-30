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
    private Long id;

    @NotNull
    @Column(name = "cNmUsuario")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "nCdGestor")
    private Usuario gestor;

    @NotNull
    @Column(name = "bGestor")
    private Boolean booleanGestor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nCdEmpresa", nullable = false)
    private Empresa empresa;

    @NotNull
    @OneToOne
    @JoinColumn(name = "nCdSetor", nullable = false)
    private Setor setor;

    @NotNull
    @OneToOne
    @JoinColumn(name = "nCdCargo", nullable = false)
    private Cargo cargo;

    @NotNull
    @Column(name = "cCPF")
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
