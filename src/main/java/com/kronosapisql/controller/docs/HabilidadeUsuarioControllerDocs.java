package com.kronosapisql.controller.docs;

import com.kronosapisql.dto.HabilidadeUsuarioDTO;
import com.kronosapisql.model.HabilidadeUsuario;
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
@Tag(name = "Habilidade Usuario", description = "Operações relacionadas ao Habilidade Usuario")
@RequestMapping("/api/habilidade-usuario")
public interface HabilidadeUsuarioControllerDocs {

    @Operation(summary = "Lista todas as habilidades usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @GetMapping("/selecionar")
    ResponseEntity<List<HabilidadeUsuario>> listarTodos();

    @Operation(summary = "Lista a habilidade do usuario pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de habilidades do usuário retornada"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/selecionar/{id}")
    ResponseEntity<List<HabilidadeUsuario>> listarPorUsuario(@PathVariable Long id);

    @Operation(summary = "Adiciona uma nova habilidade do usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Habilidades adicionadas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/adicionar")
    ResponseEntity<List<HabilidadeUsuario>> inserir(@Valid @RequestBody HabilidadeUsuarioDTO dto);

    @Operation(summary = "Atualiza uma habilidade do usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Habilidade atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Habilidade não encontrada")
    })
    @PutMapping("/atualizar")
    ResponseEntity<String> atualizarHabilidadeUsuario(@Valid @RequestBody HabilidadeUsuario habilidadeUsuario);

    @Operation(summary = "Deleta uma habilidade do usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Habilidade deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Habilidade não encontrada")
    })
    @DeleteMapping("/deletar/{id}")
    ResponseEntity<String> deletarHabilidadeUsuario(@PathVariable long id);
}
