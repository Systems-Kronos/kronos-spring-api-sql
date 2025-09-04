package com.kronosapisql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empresa")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
}
