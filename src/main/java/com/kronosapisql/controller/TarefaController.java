package com.kronosapisql.controller;

import com.kronosapisql.model.Tarefa;
import com.kronosapisql.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefa")
@Tag(name = "Tarefas", description = "Operações relacionadas as tarefas")
public class TarefaController {
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @Operation(summary = "Lista todas as tarefas")
    @GetMapping("/selecionar")
    public ResponseEntity<List<Tarefa>> listarTodasTarefas() {
        List<Tarefa> tarefas = tarefaService.listarTodasTarefas();
        if (tarefas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tarefas);
    }

    @Operation(summary = "Busca tarefa pelo ID")
    @GetMapping("/selecionar/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable String id) {
        return tarefaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Inserir uma nova tarefa")
    @PostMapping("/adicionar")
    public ResponseEntity<Tarefa> inserirTarefa(@RequestBody Tarefa tarefa) {
        Tarefa salvo = tarefaService.salvar(tarefa);
        return ResponseEntity.status(201).body(salvo);
    }

    @Operation(summary = "Atualiza uma tarefa")
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@Valid @RequestBody Tarefa tarefa) {
        tarefaService.atualizar(tarefa);
        return ResponseEntity.ok("Tarefa atualizada com sucesso.");
    }


    @Operation(summary = "Deleta uma tarefa pelo ID")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable String id) {
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
