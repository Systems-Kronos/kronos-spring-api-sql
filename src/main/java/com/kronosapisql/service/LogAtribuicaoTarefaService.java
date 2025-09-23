package com.kronosapisql.service;

import com.kronosapisql.model.LogAtribuicaoTarefa;
import com.kronosapisql.model.Usuario;
import com.kronosapisql.repository.LogAtribuicaoTarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogAtribuicaoTarefaService {
    private final LogAtribuicaoTarefaRepository logAtribuicaoTarefaRepository;

    public LogAtribuicaoTarefaService(LogAtribuicaoTarefaRepository logAtribuicaoTarefaRepository) {
        this.logAtribuicaoTarefaRepository = logAtribuicaoTarefaRepository;
    }

    public List<LogAtribuicaoTarefa> selecionar() {
        return logAtribuicaoTarefaRepository.findAll();
    }

    public Optional<LogAtribuicaoTarefa> selecionarPeloId(Long id) {
        return logAtribuicaoTarefaRepository.findById(id);
    }

    public LogAtribuicaoTarefa salvar(LogAtribuicaoTarefa logAtribuicaoTarefa) {
        return logAtribuicaoTarefaRepository.save(logAtribuicaoTarefa);
    }

    public void deletar(Long id) {
        this.logAtribuicaoTarefaRepository.deleteById(id);
    }
    public void atualizar(LogAtribuicaoTarefa logAtribuicaoTarefa) {
        this.logAtribuicaoTarefaRepository.save(logAtribuicaoTarefa);
    }
}
