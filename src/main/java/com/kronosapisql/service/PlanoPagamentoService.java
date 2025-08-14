package com.kronosapisql.service;

import com.kronosapisql.model.PlanoPagamento;
import com.kronosapisql.repository.PlanoPagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanoPagamentoService {
    private PlanoPagamentoRepository planoPagamentoRepository;

    public PlanoPagamentoService(PlanoPagamentoRepository planoPagamentoRepository) {
        this.planoPagamentoRepository = planoPagamentoRepository;
    }

    public Optional<PlanoPagamento> selecionarPeloId(Long id) {
        return this.planoPagamentoRepository.findById(id);
    }

    public List<PlanoPagamento> selecionar() {
        return this.planoPagamentoRepository.findAll();
    }

    public PlanoPagamento salvar(PlanoPagamento planoPagamento) {
        return this.planoPagamentoRepository.save(planoPagamento);
    }

    public void deletar(Long id) {
        this.planoPagamentoRepository.deleteById(id);
    }

    public void atualizar(PlanoPagamento planoPagamento) {
        this.planoPagamentoRepository.save(planoPagamento);
    }
}
