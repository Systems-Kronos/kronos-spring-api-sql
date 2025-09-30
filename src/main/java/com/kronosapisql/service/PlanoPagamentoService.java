package com.kronosapisql.service;

import com.kronosapisql.model.PlanoPagamento;
import com.kronosapisql.repository.PlanoPagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanoPagamentoService {
    private final PlanoPagamentoRepository planoPagamentoRepository;

    public PlanoPagamentoService(PlanoPagamentoRepository planoPagamentoRepository) {
        this.planoPagamentoRepository = planoPagamentoRepository;
    }

    public PlanoPagamento buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return planoPagamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado com ID: " + id));
    }

    public List<PlanoPagamento> listar() {
        return planoPagamentoRepository.findAll();
    }

    public PlanoPagamento salvar(PlanoPagamento plano) {
        if (plano == null) {
            throw new IllegalArgumentException("Plano não pode ser nulo");
        }
        return planoPagamentoRepository.save(plano);
    }

    public PlanoPagamento atualizar(PlanoPagamento plano) {
        if (plano == null) {
            throw new IllegalArgumentException("Plano não pode ser nulo");
        }
        if (!planoPagamentoRepository.existsById(plano.getId())) {
            throw new EntityNotFoundException("Plano não encontrado com ID: " + plano.getId());
        }
        return planoPagamentoRepository.save(plano);
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (!planoPagamentoRepository.existsById(id)) {
            throw new EntityNotFoundException("Plano não encontrado com ID: " + id);
        }
        planoPagamentoRepository.deleteById(id);
    }
}
