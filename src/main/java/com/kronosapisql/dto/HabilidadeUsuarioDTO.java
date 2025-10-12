package com.kronosapisql.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabilidadeUsuarioDTO {
    private Long idUsuario;
    private List<Long> idsHabilidade;
}
