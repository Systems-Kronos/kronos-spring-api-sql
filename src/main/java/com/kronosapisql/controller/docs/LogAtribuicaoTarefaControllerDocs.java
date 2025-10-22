package com.kronosapisql.controller.docs;

import com.kronosapisql.dto.LogAtribuicaoTarefaDTO;
import com.kronosapisql.dto.LogAtribuicaoTarefaResponse;
import com.kronosapisql.model.LogAtribuicaoTarefa;
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
@Tag(name = "Log Atribuição Tarefa", description = "Operações relacionadas ao Log de Atribuição das Tarefa")
@RequestMapping("/api/log-atribuicao")
public interface LogAtribuicaoTarefaControllerDocs {

    @Operation(summary = "Lista o log pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Log retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Log não encontrado")
    })
    @GetMapping("/selecionar/{id}")
    ResponseEntity<LogAtribuicaoTarefa> selecionarPeloId(@PathVariable Long id);

    @Operation(summary = "Lista o log pelo id da tarefa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Logs retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @GetMapping("/selecionarTarefa/{id}")
    ResponseEntity<List<LogAtribuicaoTarefaResponse>> buscarPorIdTarefa(@PathVariable Long id);

    @Operation(summary = "Adiciona um novo log")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Log adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/adicionar")
    ResponseEntity<LogAtribuicaoTarefaDTO> adicionarLog(@RequestBody LogAtribuicaoTarefaDTO dto);

    @Operation(summary = "Atualiza um log")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Log atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Log não encontrado")
    })
    @PutMapping("/atualizar")
    ResponseEntity<String> atualizarLog(@Valid @RequestBody LogAtribuicaoTarefa logAtribuicaoTarefa);

    @Operation(summary = "Deleta um log")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Log deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Log não encontrado")
    })
    @DeleteMapping("/deletar/{id}")
    ResponseEntity<String> deletarLog(@PathVariable long id);
}
