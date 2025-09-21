package com.kronosapisql.dto;

import java.time.LocalDateTime;

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

    // construtor vazio
    public TarefaFunctionDTO() {}

    public TarefaFunctionDTO(Long id, String nome, Long usuarioRelator,
                             Integer gravidade, Integer urgencia, Integer tendencia,
                             Integer tempoEstimado, String descricao, String status,
                             LocalDateTime dataAtribuicao, LocalDateTime dataConclusao,
                             String origemTarefa) {
        this.id = id;
        this.nome = nome;
        this.usuarioRelator = usuarioRelator;
        this.gravidade = gravidade;
        this.urgencia = urgencia;
        this.tendencia = tendencia;
        this.tempoEstimado = tempoEstimado;
        this.descricao = descricao;
        this.status = status;
        this.dataAtribuicao = dataAtribuicao;
        this.dataConclusao = dataConclusao;
        this.origemTarefa = origemTarefa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getUsuarioRelator() {
        return usuarioRelator;
    }

    public void setUsuarioRelator(Long usuarioRelator) {
        this.usuarioRelator = usuarioRelator;
    }

    public Integer getGravidade() {
        return gravidade;
    }

    public void setGravidade(Integer gravidade) {
        this.gravidade = gravidade;
    }

    public Integer getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(Integer urgencia) {
        this.urgencia = urgencia;
    }

    public Integer getTendencia() {
        return tendencia;
    }

    public void setTendencia(Integer tendencia) {
        this.tendencia = tendencia;
    }

    public Integer getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(Integer tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataAtribuicao() {
        return dataAtribuicao;
    }

    public void setDataAtribuicao(LocalDateTime dataAtribuicao) {
        this.dataAtribuicao = dataAtribuicao;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public String getOrigemTarefa() {
        return origemTarefa;
    }

    public void setOrigemTarefa(String origemTarefa) {
        this.origemTarefa = origemTarefa;
    }
}
