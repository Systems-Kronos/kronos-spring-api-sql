package com.kronosapisql.controller.docs;

import com.kronosapisql.model.Setor;
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
@Tag(name = "Setor", description = "Operações relacionadas ao setor")
@RequestMapping("/api/setor")
public interface SetorControllerDocs {

    @Operation(summary = "Lista todos os setores")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Setores retornados com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum setor encontrado")
    })
    @GetMapping("/listar")
    List<Setor> listarSetor();

    @Operation(summary = "Lista o setor pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Setor retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Setor não encontrado")
    })
    @GetMapping("/selecionar/{id}")
    ResponseEntity<Setor> buscarPorId(@PathVariable Long id);

    @Operation(summary = "Lista todos os setores pertencentes a uma empresa pelo id dela")
    @GetMapping("/selecionar/empresa/{id}")
    List<Setor> buscarPorEmpresaId(@PathVariable Long id);

    @Operation(summary = "Adiciona um setor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Setor adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/adicionar")
    ResponseEntity<String> adicionarSetor(@RequestBody @Valid Setor setor);

    @Operation(summary = "Atualiza um setor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Setor atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Setor não encontrado")
    })
    @PutMapping("/atualizar")
    ResponseEntity<String> atualizarSetor(@RequestBody @Valid Setor setor);

    @Operation(summary = "Deleta um setor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Setor removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Setor não encontrado")
    })
    @DeleteMapping("/deletar/{id}")
    ResponseEntity<String> deletarSetor(@PathVariable long id);
}
