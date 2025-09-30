package com.kronosapisql.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaFunctionDTO {
    private Long id;
    private String nome;
    private Long usuarioRelator;
    private Integer gravidade;
    private Integer urgencia;
    private Integer tendencia;
    private Integer tempoEstimado;
    private String descricao;
    private String status;
    private LocalDateTime dataAtribuicao;
    private LocalDateTime dataConclusao;
    private String origemTarefa;

    public static TarefaFunctionDTO fromRow(Object[] row) {
        return TarefaFunctionDTO.builder()
                .id(((Number) row[0]).longValue())
                .nome((String) row[1])
                .usuarioRelator(((Number) row[2]).longValue())
                .gravidade(row[3] != null ? ((Number) row[3]).intValue() : null)
                .urgencia(row[4] != null ? ((Number) row[4]).intValue() : null)
                .tendencia(row[5] != null ? ((Number) row[5]).intValue() : null)
                .tempoEstimado(row[6] != null ? ((Number) row[6]).intValue() : null)
                .descricao((String) row[7])
                .status((String) row[8])
                .dataAtribuicao(row[9] != null ? ((Timestamp) row[9]).toLocalDateTime() : null)
                .dataConclusao(row[10] != null ? ((Timestamp) row[10]).toLocalDateTime() : null)
                .origemTarefa((String) row[11])
                .build();
    }
}
