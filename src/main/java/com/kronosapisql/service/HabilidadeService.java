package com.kronosapisql.service;

import com.kronosapisql.model.Habilidade;
import com.kronosapisql.repository.HabilidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabilidadeService {
    private HabilidadeRepository habilidadeRepository;

    public HabilidadeService(HabilidadeRepository habilidadeRepository) {
        this.habilidadeRepository = habilidadeRepository;
    }

    public Optional<Habilidade> selecionarPeloId(Long id) {
        return this.habilidadeRepository.findById(id);
    }

    public List<Habilidade> selecionar() {
        return this.habilidadeRepository.findAll();
    }

    public Habilidade salvar(Habilidade habilidade) {
        return this.habilidadeRepository.save(habilidade);
    }

    public void deletar(Long id) {
        this.habilidadeRepository.deleteById(id);
    }

    public void atualizar(Habilidade habilidade) {
        this.habilidadeRepository.save(habilidade);
    }
}
