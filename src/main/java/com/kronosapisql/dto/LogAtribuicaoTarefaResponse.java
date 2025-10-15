package com.kronosapisql.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogAtribuicaoTarefaResponse {
    private Long id;
    private Long idTarefa;
    private Long idUsuarioAtribuido;
    private String nomeUsuarioAtribuido;
    private Date dataRealocacao;
    private String observacao;
}

