package com.kronosapisql.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
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
    private Integer tempoEstimado;

    @NotNull
    private String status;

    @NotNull
    private Date dataAtribuicao;

    @NotNull
    private List<TarefaHabilidadeDTO> habilidades;

    @NotNull
    private List<Integer> usuariosResponsaveis;
}
