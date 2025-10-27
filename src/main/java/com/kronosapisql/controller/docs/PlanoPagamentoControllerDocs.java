package com.kronosapisql.controller.docs;

import com.kronosapisql.model.PlanoPagamento;
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
@Tag(name = "Plano", description = "Operações relacionadas ao plano de pagamento")
@RequestMapping("/api/plano")
public interface PlanoPagamentoControllerDocs {

    @Operation(summary = "Lista todos os planos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Planos listados com sucesso")
    })
    @GetMapping("/listar/")
    List<PlanoPagamento> listarPlano();

    @Operation(summary = "Lista um plano pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Plano retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Plano não encontrado")
    })
    @GetMapping("/selecionar/{id}")
    ResponseEntity<PlanoPagamento> selecionarPeloId(@PathVariable Long id);

    @Operation(summary = "Adiciona um plano")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Plano adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/adicionar")
    ResponseEntity<String> adicionarPlano(@Valid @RequestBody PlanoPagamento planoPagamento);

    @Operation(summary = "Atualiza um plano")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Plano atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Plano não encontrado")
    })
    @PutMapping("/atualizar")
    ResponseEntity<String> atualizarPlano(@Valid @RequestBody PlanoPagamento planoPagamento);

    @Operation(summary = "Deleta um plano")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Plano deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Plano não encontrado")
    })
    @DeleteMapping("/deletar/{id}")
    ResponseEntity<String> deletarPlano(@PathVariable long id);
}
