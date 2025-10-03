package com.kronosapisql.controller;

import com.kronosapisql.dto.TarefaFunctionDTO;
import com.kronosapisql.dto.TarefaRequestDTO;
import com.kronosapisql.model.Tarefa;
import com.kronosapisql.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefa")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Tarefas", description = "Operações relacionadas as tarefas")
public class TarefaController {
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @Operation(summary = "Lista todas as tarefas de um usuário baseada na function")
    @GetMapping("/selecionarFunction/{usuarioId}")
    public List<TarefaFunctionDTO> listarTarefasUsuario(@PathVariable Long usuarioId, @RequestParam(defaultValue = "1") String tipoTarefa, @RequestParam(defaultValue = "1") String status) {
        return tarefaService.listarTarefasUsuario(usuarioId, tipoTarefa, status);
    }

    @Operation(summary = "Lista todas as tarefas dos usuários de um gestor específico baseada na function")
    @GetMapping("/selecionarFunctionGestor/{idGestor}")
    public List<TarefaFunctionDTO> listarTarefasUsuarioGestor(@PathVariable Long idGestor, @RequestParam(defaultValue = "1") String tipoTarefa, @RequestParam(defaultValue = "4") String status) {
        return tarefaService.listarTarefasUsuarioGestor(idGestor, tipoTarefa, status);
    }

    @Operation(summary = "Busca tarefa pelo ID")
    @GetMapping("/selecionar/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
        Tarefa tarefa = tarefaService.buscarPorId(id);
        return ResponseEntity.ok(tarefa);
    }

    @Operation(summary = "Inserir uma nova tarefa")
    @PostMapping("/adicionar")
    public ResponseEntity<Tarefa> inserirTarefa(@RequestBody TarefaRequestDTO dto) {
        Tarefa salvo = tarefaService.salvar(dto);
        return ResponseEntity.status(201).body(salvo);
    }

    @Operation(summary = "Atualiza uma tarefa")
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizarTarefa(@Valid @RequestBody Tarefa tarefa) {
        tarefaService.atualizar(tarefa);
        return ResponseEntity.ok("Tarefa atualizada com sucesso.");
    }

    @Operation(summary = "Deleta uma tarefa pelo ID")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
