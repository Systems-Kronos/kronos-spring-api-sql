package com.kronosapisql.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefaHabilidadeDTO {
    private Long idHabilidade;
    private Integer prioridade;
}
