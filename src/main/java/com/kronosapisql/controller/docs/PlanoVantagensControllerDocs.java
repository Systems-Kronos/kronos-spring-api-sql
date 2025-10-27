package com.kronosapisql.controller.docs;

import com.kronosapisql.model.PlanoVantagens;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Vantagens", description = "Operações relacionadas às vantagens dos planos")
@RequestMapping("/api/vantagens")
public interface PlanoVantagensControllerDocs {

    @Operation(summary = "Lista uma vantagem pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vantagem retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Vantagem não encontrada")
    })
    @GetMapping("/selecionar/{id}")
    ResponseEntity<PlanoVantagens> selecionarPeloId(@PathVariable Long id);

    @Operation(summary = "Adiciona uma vantagem")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Vantagem adicionada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/adicionar")
    ResponseEntity<String> adicionarVantagens(@Valid @RequestBody PlanoVantagens planoVantagens);

    @Operation(summary = "Atualiza uma vantagem")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vantagem atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Vantagem não encontrada")
    })
    @PutMapping("/atualizar")
    ResponseEntity<String> atualizarVantagens(@Valid @RequestBody PlanoVantagens planoVantagens);

    @Operation(summary = "Deleta uma vantagem")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vantagem deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Vantagem não encontrada")
    })
    @DeleteMapping("/deletar/{id}")
    ResponseEntity<String> deletarVantagens(@PathVariable long id);
}
