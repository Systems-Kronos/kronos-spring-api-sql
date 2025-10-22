package com.kronosapisql.controller.docs;

import com.kronosapisql.model.Mensagem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Mensagem", description = "Operações relacionadas à mensagem")
@RequestMapping("/api/mensagem")
public interface MensagemControllerDocs {

    @Operation(summary = "Lista a mensagem pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mensagem retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Mensagem não encontrada")
    })
    @GetMapping("/selecionar/{id}")
    ResponseEntity<Mensagem> selecionarPeloId(@PathVariable Long id);

    @Operation(summary = "Adiciona uma nova mensagem")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Mensagem adicionada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/adicionar")
    ResponseEntity<String> adicionarMensagem(@Valid @RequestBody Mensagem mensagem);

    @Operation(summary = "Atualiza uma mensagem")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mensagem atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Mensagem não encontrada")
    })
    @PutMapping("/atualizar")
    ResponseEntity<String> atualizarMensagem(@Valid @RequestBody Mensagem mensagem);

    @Operation(summary = "Deleta uma mensagem")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mensagem deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Mensagem não encontrada")
    })
    @DeleteMapping("/deletar/{id}")
    ResponseEntity<String> deletarMensagem(@PathVariable long id);
}
