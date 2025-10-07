package com.kronosapisql.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportFunctionDTO {
    private Long id;
    private Long idTarefa;
    private String tituloTarefa;
    private Long idUsuario;
    private String nomeUsuario;
    private String fotoUsuario;
    private String descricao;
    private String problema;
    private String status;

    public static ReportFunctionDTO fromRow(Object[] row) {
        return ReportFunctionDTO.builder()
                .id(row[0] != null ? ((Number) row[0]).longValue() : null)
                .idTarefa(row[1] != null ? ((Number) row[1]).longValue() : null)
                .tituloTarefa(row[2] != null ? row[2].toString() : null)
                .idUsuario(row[3] != null ? ((Number) row[3]).longValue() : null)
                .nomeUsuario(row[4] != null ? row[4].toString() : null)
                .fotoUsuario(row[5] != null ? row[5].toString() : null)
                .descricao(row[6] != null ? row[6].toString() : null)
                .problema(row[7] != null ? row[7].toString() : null)
                .status(row[8] != null ? row[8].toString() : null)
                .build();
    }
}
