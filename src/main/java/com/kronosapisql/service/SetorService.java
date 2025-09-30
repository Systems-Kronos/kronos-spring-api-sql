package com.kronosapisql.service;

import com.kronosapisql.model.Setor;
import com.kronosapisql.repository.SetorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetorService {
    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public Setor buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return setorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado com ID: " + id));
    }

    public List<Setor> buscarPeloIdEmpresa(Long id) {
        return setorRepository.findByEmpresaId(id);
    }

    public List<Setor> listar() {
        return setorRepository.findAll();
    }

    public Setor salvar(Setor setor) {
        if (setor == null) {
            throw new IllegalArgumentException("Setor não pode ser nulo");
        }
        return setorRepository.save(setor);
    }

    public Setor atualizar(Setor setor) {
        if (setor == null) {
            throw new IllegalArgumentException("Setor não pode ser nulo");
        }
        if (!setorRepository.existsById(setor.getId())) {
            throw new EntityNotFoundException("Setor não encontrado com ID: " + setor.getId());
        }
        return setorRepository.save(setor);
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (!setorRepository.existsById(id)) {
            throw new EntityNotFoundException("Setor não encontrado com ID: " + id);
        }
        setorRepository.deleteById(id);
    }
}
