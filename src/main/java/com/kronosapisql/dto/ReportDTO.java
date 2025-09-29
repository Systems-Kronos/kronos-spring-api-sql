package com.kronosapisql.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDTO {
    private Long idTarefa;
    private String descricao;
    private String problema;
    private String status;
}
