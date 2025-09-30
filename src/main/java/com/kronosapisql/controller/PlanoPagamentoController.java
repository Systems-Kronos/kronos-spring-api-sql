package com.kronosapisql.controller;

import com.kronosapisql.model.PlanoPagamento;
import com.kronosapisql.service.PlanoPagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plano")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Plano", description = "Operações relacionadas a plano pagamento")
public class PlanoPagamentoController {
    private final PlanoPagamentoService planoPagamentoService;

    public PlanoPagamentoController(PlanoPagamentoService planoPagamentoService) {
        this.planoPagamentoService = planoPagamentoService;
    }

    @GetMapping("/listar/")
    @Operation(summary = "Lista todos os planos")
    public List<PlanoPagamento> listarPlano() {
        return planoPagamentoService.listar();
    }

    @GetMapping("/selecionar/{id}")
    @Operation(summary = "Lista um plano pelo id")
    public ResponseEntity<PlanoPagamento> selecionarPeloId(@PathVariable Long id) {
        PlanoPagamento planoPagamento = planoPagamentoService.buscarPorId(id);
        return ResponseEntity.ok(planoPagamento);
    }

    @Operation(summary = "Adiciona um plano")
    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarPlano(@RequestBody @Valid PlanoPagamento planoPagamento) {
        planoPagamentoService.salvar(planoPagamento);
        return ResponseEntity.ok("Plano adicionado com sucesso");
    }

    @Operation(summary = "Atualiza um plano")
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizarPlano(@Valid @RequestBody PlanoPagamento planoPagamento) {
        planoPagamentoService.atualizar(planoPagamento);
        return ResponseEntity.ok("Plano atualizada com sucesso.");
    }

    @Operation(summary = "Deleta um plano")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarPlano(@PathVariable long id) {
        planoPagamentoService.deletar(id);
        return ResponseEntity.ok("Plano deletada com sucesso.");
    }
}
