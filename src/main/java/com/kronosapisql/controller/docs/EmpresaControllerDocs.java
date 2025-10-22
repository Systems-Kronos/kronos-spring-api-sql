package com.kronosapisql.controller.docs;

import com.kronosapisql.model.Empresa;
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
@Tag(name = "Empresa", description = "Operações relacionadas à empresa")
@RequestMapping("/api/empresa")
public interface EmpresaControllerDocs {

    @Operation(summary = "Lista todas as empresas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de empresas retornada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @GetMapping("/listar")
    List<Empresa> listarEmpresa();

    @Operation(summary = "Lista uma empresa pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
    })
    @GetMapping("/selecionar/{id}")
    ResponseEntity<Empresa> selecionarPeloId(@PathVariable Long id);

    @Operation(summary = "Adiciona uma nova empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa adicionada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/adicionar")
    ResponseEntity<String> adicionarEmpresa(@RequestBody @Valid Empresa empresa);

    @Operation(summary = "Atualiza uma empresa existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping("/atualizar")
    ResponseEntity<String> atualizarEmpresa(@RequestBody @Valid Empresa empresa);

    @Operation(summary = "Deleta uma empresa pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
    })
    @DeleteMapping("/deletar/{id}")
    ResponseEntity<String> deletarEmpresa(@PathVariable Long id);
}
