package com.kronosapisql.controller.docs;

import com.kronosapisql.dto.StatusUpdateDTO;
import com.kronosapisql.dto.TarefaFunctionDTO;
import com.kronosapisql.dto.TarefaRequestDTO;
import com.kronosapisql.model.Tarefa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Tarefas", description = "Operações relacionadas às tarefas")
@RequestMapping("/api/tarefa")
public interface TarefaControllerDocs {

    @Operation(summary = "Lista todas as tarefas de um usuário baseada na function")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefas retornadas com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma tarefa encontrada")
    })
    @GetMapping("/selecionarFunction/{usuarioId}")
    List<TarefaFunctionDTO> listarTarefasUsuario(
            @PathVariable Long usuarioId,
            @RequestParam(defaultValue = "1") String tipoTarefa,
            @RequestParam(defaultValue = "4") String status);

    @Operation(summary = "Lista todas as tarefas dos usuários de um gestor específico baseada na function")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefas retornadas com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma tarefa encontrada")
    })
    @GetMapping("/selecionarFunctionGestor/{idGestor}")
    List<TarefaFunctionDTO> listarTarefasUsuarioGestor(
            @PathVariable Long idGestor,
            @RequestParam(defaultValue = "1") String tipoTarefa,
            @RequestParam(defaultValue = "4") String status);

    @Operation(summary = "Busca tarefa pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @GetMapping("/selecionar/{id}")
    ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id);

    @Operation(summary = "Inserir uma nova tarefa")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/adicionar")
    ResponseEntity<Tarefa> inserirTarefa(@RequestBody @Valid TarefaRequestDTO dto);

    @Operation(summary = "Atualiza uma tarefa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @PutMapping("/atualizar")
    ResponseEntity<String> atualizarTarefa(@Valid @RequestBody Tarefa tarefa);

    @Operation(summary = "Atualiza o status de uma tarefa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status da tarefa atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @PutMapping("/atualizarStatus/{id}")
    ResponseEntity<String> atualizarStatus(@PathVariable Long id, @RequestBody StatusUpdateDTO dto);

    @Operation(summary = "Deleta uma tarefa pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tarefa deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @DeleteMapping("/deletar/{id}")
    ResponseEntity<Void> deletarTarefa(@PathVariable Long id);
}
