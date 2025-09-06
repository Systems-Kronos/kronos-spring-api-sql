package com.kronosapisql.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefaHabilidadeDTO {
    private Long idHabilidade;
    private Integer prioridade;
}
