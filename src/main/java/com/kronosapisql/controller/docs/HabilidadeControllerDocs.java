package com.kronosapisql.controller.docs;

import com.kronosapisql.model.Habilidade;
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
@Tag(name = "Habilidade", description = "Operações relacionadas à habilidade")
@RequestMapping("/api/habilidade")
public interface HabilidadeControllerDocs {

    @Operation(summary = "Lista todas as habilidades")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de habilidades retornada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/listar")
    List<Habilidade> listarHabilidade();

    @Operation(summary = "Busca uma habilidade pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habilidade encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Habilidade não encontrada"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @GetMapping("/selecionar/{id}")
    ResponseEntity<Habilidade> selecionarPeloId(@PathVariable Long id);

    @Operation(summary = "Lista todas as habilidades pertencentes a uma empresa pelo ID dela")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de habilidades retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @GetMapping("/selecionar/empresa/{id}")
    List<Habilidade> buscarPorEmpresaId(@PathVariable Long id);

    @Operation(summary = "Adiciona uma nova habilidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habilidade adicionada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @PostMapping("/adicionar")
    ResponseEntity<String> adicionarHabilidade(@RequestBody @Valid Habilidade habilidade);

    @Operation(summary = "Atualiza uma habilidade existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habilidade atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Habilidade não encontrada"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @PutMapping("/atualizar")
    ResponseEntity<String> atualizarHabilidade(@RequestBody @Valid Habilidade habilidade);

    @Operation(summary = "Deleta uma habilidade pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Habilidade removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Habilidade não encontrada"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @DeleteMapping("/deletar/{id}")
    ResponseEntity<String> deletarHabilidade(@PathVariable long id);
}
