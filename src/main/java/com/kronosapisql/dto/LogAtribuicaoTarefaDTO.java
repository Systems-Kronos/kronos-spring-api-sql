package com.kronosapisql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogAtribuicaoTarefaDTO {
    private Long id;
    private Long idTarefa;
    private Long idUsuarioAtribuido;
    private Date dataRealocacao;
    private String observacao;
}
