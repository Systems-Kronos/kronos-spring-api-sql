package com.kronosapisql.controller;

import com.kronosapisql.controller.docs.PlanoPagamentoControllerDocs;
import com.kronosapisql.model.PlanoPagamento;
import com.kronosapisql.service.PlanoPagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlanoPagamentoController implements PlanoPagamentoControllerDocs {

    private final PlanoPagamentoService planoPagamentoService;

    public PlanoPagamentoController(PlanoPagamentoService planoPagamentoService) {
        this.planoPagamentoService = planoPagamentoService;
    }

    @Override
    public List<PlanoPagamento> listarPlano() {
        return planoPagamentoService.listar();
    }

    @Override
    public ResponseEntity<PlanoPagamento> selecionarPeloId(Long id) {
        return ResponseEntity.ok(planoPagamentoService.buscarPorId(id));
    }

    @Override
    public ResponseEntity<String> adicionarPlano(PlanoPagamento planoPagamento) {
        planoPagamentoService.salvar(planoPagamento);
        return ResponseEntity.ok("Plano adicionado com sucesso");
    }

    @Override
    public ResponseEntity<String> atualizarPlano(PlanoPagamento planoPagamento) {
        planoPagamentoService.atualizar(planoPagamento);
        return ResponseEntity.ok("Plano atualizado com sucesso.");
    }

    @Override
    public ResponseEntity<String> deletarPlano(long id) {
        planoPagamentoService.deletar(id);
        return ResponseEntity.ok("Plano deletado com sucesso.");
    }
}
