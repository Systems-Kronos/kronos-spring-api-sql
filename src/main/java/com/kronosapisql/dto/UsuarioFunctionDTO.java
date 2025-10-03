package com.kronosapisql.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioFunctionDTO {
    private Long id;
    private String nome;
    private Long idSetor;
    private String nomeSetor;
    private Long idGestor;
    private Boolean booleanGestor;
    private Long idCargo;
    private String nomeCargo;
    private String telefone;
    private String email;
    private String foto;
    private Boolean ativo;

    public static UsuarioFunctionDTO fromRow(Object[] row) {
        return UsuarioFunctionDTO.builder()
                .id(row[0] != null ? ((Number) row[0]).longValue() : null)
                .nome(row[1] != null ? row[1].toString() : null)
                .idSetor(row[2] != null ? ((Number) row[2]).longValue() : null)
                .nomeSetor(row[3] != null ? row[3].toString() : null)
                .idGestor(row[4] != null ? ((Number) row[4]).longValue() : null)
                .booleanGestor(row[5] != null ? (Boolean) row[5] : null)
                .idCargo(row[6] != null ? ((Number) row[6]).longValue() : null)
                .nomeCargo(row[7] != null ? row[7].toString() : null)
                .telefone(row[11] != null ? row[11].toString() : null)
                .email(row[12] != null ? row[12].toString() : null)
                .foto(row[13] != null ? row[13].toString() : null)
                .ativo(row[14] != null ? (Boolean) row[14] : null)
                .build();
    }
}
