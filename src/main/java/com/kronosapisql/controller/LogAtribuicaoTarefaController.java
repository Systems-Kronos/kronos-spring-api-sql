package com.kronosapisql.controller;

import com.kronosapisql.dto.LogAtribuicaoTarefaDTO;
import com.kronosapisql.dto.LogAtribuicaoTarefaResponse;
import com.kronosapisql.model.LogAtribuicaoTarefa;
import com.kronosapisql.service.LogAtribuicaoTarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<LogAtribuicaoTarefa> selecionarPeloId(@PathVariable Long id) {
        LogAtribuicaoTarefa logAtribuicaoTarefa = logAtribuicaoTarefaService.buscarPorId(id);
        return ResponseEntity.ok(logAtribuicaoTarefa);
    }

    @GetMapping("/selecionarTarefa/{id}")
    @Operation(summary = "Lista o log pelo id da tarefa")
    public ResponseEntity<List<LogAtribuicaoTarefaResponse>> buscarPorIdTarefa(@PathVariable Long id) {
        List<LogAtribuicaoTarefaResponse> resposta = logAtribuicaoTarefaService.buscarPorIdTarefa(id);
        return ResponseEntity.ok(resposta);
    }

    @Operation(summary = "Adiciona um novo log")
    @PostMapping("/adicionar")
    public ResponseEntity<LogAtribuicaoTarefaDTO> adicionarLog(@RequestBody LogAtribuicaoTarefaDTO dto) {
        try {
            LogAtribuicaoTarefaDTO salvo = logAtribuicaoTarefaService.adicionarLog(dto);
            return new ResponseEntity<>(salvo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Atualiza um log")
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizarLog(@Valid @RequestBody LogAtribuicaoTarefa logAtribuicaoTarefa) {
        logAtribuicaoTarefaService.atualizar(logAtribuicaoTarefa);
        return ResponseEntity.ok("Log atualizado com sucesso.");
    }

    @Operation(summary = "Deleta um log")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarLog(@PathVariable long id) {
        logAtribuicaoTarefaService.deletar(id);
        return ResponseEntity.ok("Log deletado com sucesso.");
    }
}
