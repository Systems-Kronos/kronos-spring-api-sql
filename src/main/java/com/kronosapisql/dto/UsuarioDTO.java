package com.kronosapisql.dto;

import com.kronosapisql.model.Cargo;
import com.kronosapisql.model.Empresa;
import com.kronosapisql.model.Setor;
import com.kronosapisql.model.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    private Long id;
    private String nome;
    private Long gestorId;
    private Boolean booleanGestor;
    private Long empresaId;
    private Long setorId;
    private Long cargoId;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;
    private String foto;
    private Boolean ativo;
}
