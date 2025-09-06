package com.kronosapisql.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefaRequestDTO {
    @NotNull
    private String nome;
    @NotNull
    private String descricao;
    @NotNull
    private Long idUsuarioRelator;
    @NotNull
    private Integer gravidade;
    @NotNull
    private Integer urgencia;
    @NotNull
    private Integer tendencia;
    @NotNull
    private Double tempoEstimado;
    @NotNull
    private List<TarefaHabilidadeDTO> habilidades; // lista de habilidades com prioridade
}
