package com.kronosapisql.controller;


import com.kronosapisql.model.LogAtribuicaoTarefa;
import com.kronosapisql.service.LogAtribuicaoTarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/log-atribuicao")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Log Atribuição Tarefa", description = "Operações relacionadas ao Log de Atribuição das Tarefa")

public class LogAtribuicaoTarefaController {

    private final LogAtribuicaoTarefaService logAtribuicaoTarefaService;

    public LogAtribuicaoTarefaController(LogAtribuicaoTarefaService logAtribuicaoTarefaService) {
        this.logAtribuicaoTarefaService = logAtribuicaoTarefaService;
    }

    @GetMapping("/selecionar/{id}")
    @Operation(summary = "Lista o log pelo id")
    public Optional<LogAtribuicaoTarefa> selecionarPeloId(@PathVariable Long id) {
        return logAtribuicaoTarefaService.selecionarPeloId(id);
    }

    @Operation(summary = "Adiciona um novo log")
    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionar(@Valid @RequestBody LogAtribuicaoTarefa logAtribuicaoTarefa) {
        logAtribuicaoTarefaService.salvar(logAtribuicaoTarefa);
        return ResponseEntity.ok("Log adicionado com sucesso.");
    }

    @Operation(summary = "Atualiza um log")
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@Valid @RequestBody LogAtribuicaoTarefa logAtribuicaoTarefa) {
        logAtribuicaoTarefaService.atualizar(logAtribuicaoTarefa);
        return ResponseEntity.ok("Log atualizado com sucesso.");
    }

    @Operation(summary = "Deleta um log")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable long id) {
        logAtribuicaoTarefaService.deletar(id);
        return ResponseEntity.ok("Log deletado com sucesso.");
    }
}




