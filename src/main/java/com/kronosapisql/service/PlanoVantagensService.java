package com.kronosapisql.service;

import com.kronosapisql.model.PlanoVantagens;
import com.kronosapisql.repository.PlanoVantagensRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanoVantagensService {
    private final PlanoVantagensRepository planoVantagensRepository;

    public PlanoVantagensService(PlanoVantagensRepository planoVantagensRepository) {
        this.planoVantagensRepository = planoVantagensRepository;
    }

    public PlanoVantagens buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return planoVantagensRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vantagens de plano não encontrado com ID: " + id));
    }

    public PlanoVantagens salvar(PlanoVantagens plano) {
        if (plano == null) {
            throw new IllegalArgumentException("Vantagens não pode ser nula");
        }
        return planoVantagensRepository.save(plano);
    }

    public PlanoVantagens atualizar(PlanoVantagens plano) {
        if (plano == null) {
            throw new IllegalArgumentException("Vantagens não pode ser nula");
        }
        if (!planoVantagensRepository.existsById(plano.getId())) {
            throw new EntityNotFoundException("Plano não encontrado com ID: " + plano.getId());
        }
        return planoVantagensRepository.save(plano);
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (!planoVantagensRepository.existsById(id)) {
            throw new EntityNotFoundException("Plano não encontrado com ID: " + id);
        }
        planoVantagensRepository.deleteById(id);
    }
}
