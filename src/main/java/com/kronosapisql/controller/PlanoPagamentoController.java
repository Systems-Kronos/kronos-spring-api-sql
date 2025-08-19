package com.kronosapisql.controller;

import com.kronosapisql.model.PlanoPagamento;
import com.kronosapisql.service.PlanoPagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/plano")
@Tag(name = "Plano", description = "Operações relacionadas a plano pagamento")
public class PlanoPagamentoController {
    private final PlanoPagamentoService planoPagamentoService;

    public PlanoPagamentoController(PlanoPagamentoService planoPagamentoService) {
        this.planoPagamentoService = planoPagamentoService;
    }

    @GetMapping("/selecionar/")
    @Operation(summary = "Lista todos os planos")
    public List<PlanoPagamento> selecionar() {
        return planoPagamentoService.selecionar();
    }

    @GetMapping("/selecionar/id/{id}")
    @Operation(summary = "Lista um plano pelo id")
    public Optional<PlanoPagamento> selecionarPeloId(@PathVariable Long id) {
        return planoPagamentoService.selecionarPeloId(id);
    }

    @Operation(summary = "Adiciona um plano")
    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionar(@RequestBody @Valid PlanoPagamento planoPagamento) {
        planoPagamentoService.salvar(planoPagamento);
        return ResponseEntity.ok("Plano adicionado com sucesso");
    }

    @Operation(summary = "Atualiza um plano")
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@Valid @RequestBody PlanoPagamento planoPagamento) {
        planoPagamentoService.atualizar(planoPagamento);
        return ResponseEntity.ok("Plano atualizada com sucesso.");
    }

    @Operation(summary = "Deleta um plano")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable long id) {
        planoPagamentoService.deletar(id);
        return ResponseEntity.ok("Plano deletada com sucesso.");
    }
}
