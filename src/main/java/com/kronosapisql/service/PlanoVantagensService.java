package com.kronosapisql.service;

import com.kronosapisql.model.PlanoVantagens;
import com.kronosapisql.repository.PlanoVantagensRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanoVantagensService {
    private PlanoVantagensRepository planoVantagensRepository;

    public PlanoVantagensService(PlanoVantagensRepository planoVantagensRepository) {
        this.planoVantagensRepository = planoVantagensRepository;
    }

    public Optional<PlanoVantagens> selecionarPeloId(Long id) {
        return this.planoVantagensRepository.findById(id);
    }

    public List<PlanoVantagens> selecionar() {
        return this.planoVantagensRepository.findAll();
    }

    public PlanoVantagens salvar(PlanoVantagens planoVantagens) {
        return this.planoVantagensRepository.save(planoVantagens);
    }

    public void deletar(Long id) {
        this.planoVantagensRepository.deleteById(id);
    }

    public void atualizar(PlanoVantagens planoVantagens) {
        this.planoVantagensRepository.save(planoVantagens);
    }
}
