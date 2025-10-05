package com.kronosapisql.dto;

import com.kronosapisql.model.OpcaoStatus;
import com.kronosapisql.model.Tarefa;
import com.kronosapisql.model.Usuario;
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
    private OpcaoStatus status;

    @NotNull
    private Date dataAtribuicao;

    @NotNull
    private List<TarefaHabilidadeDTO> habilidades;

    @NotNull
    private List<Integer> usuariosResponsaveis;

    public Tarefa toEntity(Usuario usuarioRelator) {
        return Tarefa.builder()
                .nome(this.getNome())
                .descricao(this.getDescricao())
                .usuarioRelator(usuarioRelator)
                .gravidade(this.getGravidade())
                .urgencia(this.getUrgencia())
                .tendencia(this.getTendencia())
                .tempoEstimado(this.getTempoEstimado())
                .dataAtribuicao(Date.from(this.getDataAtribuicao().toInstant()))
                .dataConclusao(null)
                .status(this.getStatus())
                .build();
    }
}
