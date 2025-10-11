package com.kronosapisql.service;

import com.kronosapisql.model.Habilidade;
import com.kronosapisql.repository.HabilidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabilidadeService {
    private final HabilidadeRepository habilidadeRepository;

    public HabilidadeService(HabilidadeRepository habilidadeRepository) {
        this.habilidadeRepository = habilidadeRepository;
    }

    public Habilidade buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return habilidadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Habilidade não encontrado com ID: " + id));
    }

    public List<Habilidade> buscarPeloIdEmpresa(Long id) {
        return habilidadeRepository.findByEmpresaId(id);
    }

    public List<Habilidade> listar() {
        return habilidadeRepository.findAll();
    }

    public Habilidade salvar(Habilidade habilidade) {
        if (habilidade == null) {
            throw new IllegalArgumentException("Habilidade não pode ser nula");
        }
        return habilidadeRepository.save(habilidade);
    }

    public Habilidade atualizar(Habilidade habilidade) {
        if (habilidade == null) {
            throw new IllegalArgumentException("Habilidade não pode ser nula");
        }
        if (!habilidadeRepository.existsById(habilidade.getId())) {
            throw new EntityNotFoundException("Habilidade não encontrado com ID: " + habilidade.getId());
        }
        return habilidadeRepository.save(habilidade);
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (!habilidadeRepository.existsById(id)) {
            throw new EntityNotFoundException("Habilidade não encontrado com ID: " + id);
        }
        habilidadeRepository.deleteById(id);
    }
}
