package com.kronosapisql.service;

import com.kronosapisql.model.Setor;
import com.kronosapisql.repository.SetorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SetorService {
    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public Optional<Setor> selecionarPeloId(Long id) {
        return this.setorRepository.findById(id);
    }

    public List<Setor> selecionar() {
        return this.setorRepository.findAll();
    }

    public Setor salvar(Setor setor) {
        return this.setorRepository.save(setor);
    }

    public void deletar(Long id) {
        this.setorRepository.deleteById(id);
    }

    public void atualizar(Setor setor) {
        this.setorRepository.save(setor);
    }

    public List<Setor> selecionarPelaEmpresaId(Long id) {
        return this.setorRepository.findByEmpresaId(id);
    }
}
