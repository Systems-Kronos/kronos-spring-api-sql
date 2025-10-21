package com.kronosapisql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "administracao")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Administracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ncdadm")
    private Long id;

    @NotNull
    @Column(name = "cnmadm")
    private String nome;

    @NotNull
    @Column(name = "cemailadm")
    private String email;


    @NotNull
    @Column(name = "csenha")
    private String senha;

}
