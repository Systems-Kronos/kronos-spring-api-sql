package com.kronosapisql.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cargo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nCdCargo")
    private Long id;

    @NotNull
    @Column(name = "cNmCargo")
    private String nome;

    @Column(name = "cCdCBO")
    private String cbo;

    @Column(name = "cNmFamiliaOcupacional")
    private String familiaOcupacional;
}
