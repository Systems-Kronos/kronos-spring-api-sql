package com.kronosapisql.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<Long> idsHabilidades;
    private List<String> nomesHabilidades;
    private String cpf;
    private String telefone;
    private String email;
    private String foto;
    private Boolean ativo;

    public static UsuarioFunctionDTO fromRow(Object[] row) {
        return UsuarioFunctionDTO.builder()
                .id(row[0] != null ? ((Number) row[0]).longValue() : null)                  // nCdUsuario
                .nome(row[1] != null ? row[1].toString() : null)                            // cNmUsuario
                .idSetor(row[2] != null ? ((Number) row[2]).longValue() : null)             // nCdSetor
                .nomeSetor(row[3] != null ? row[3].toString() : null)                       // cNmSetor
                .idGestor(row[4] != null ? ((Number) row[4]).longValue() : null)            // nCdGestor
                .booleanGestor(row[5] != null ? (Boolean) row[5] : null)                    // bGestor
                .idCargo(row[6] != null ? ((Number) row[6]).longValue() : null)             // nCdCargo
                .nomeCargo(row[7] != null ? row[7].toString() : null)                       // cNmCargo
                .telefone(row[11] != null ? row[11].toString() : null)                      // cTelefone
                .email(row[12] != null ? row[12].toString() : null)                         // cEmail
                .foto(row[13] != null ? row[13].toString() : null)                          // cFoto
                .ativo(row[14] != null ? (Boolean) row[14] : null)                          // bAtivo
                .build();
    }
}
