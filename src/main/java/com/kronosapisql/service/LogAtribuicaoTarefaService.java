package com.kronosapisql.service;

import com.kronosapisql.dto.LogAtribuicaoTarefaDTO;
import com.kronosapisql.model.LogAtribuicaoTarefa;
import com.kronosapisql.model.Tarefa;
import com.kronosapisql.repository.LogAtribuicaoTarefaRepository;
import com.kronosapisql.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogAtribuicaoTarefaService {
    private final LogAtribuicaoTarefaRepository logAtribuicaoTarefaRepository;
    private final TarefaRepository tarefaRepository;

    public LogAtribuicaoTarefaService(LogAtribuicaoTarefaRepository logAtribuicaoTarefaRepository, TarefaRepository tarefaRepository) {
        this.logAtribuicaoTarefaRepository = logAtribuicaoTarefaRepository;
        this.tarefaRepository = tarefaRepository;
    }

    public LogAtribuicaoTarefa buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return logAtribuicaoTarefaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Log de atribuição não encontrado com ID: " + id));
    }

    public List<LogAtribuicaoTarefa> listar() {
        return logAtribuicaoTarefaRepository.findAll();
    }

    public List<LogAtribuicaoTarefa> buscarPorIdTarefa(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }

        List<LogAtribuicaoTarefa> logs = logAtribuicaoTarefaRepository.findByTarefa_Id(id);

        if (logs.isEmpty()) {
            throw new EntityNotFoundException("Nenhum log de atribuição encontrado para com o ID da tarefa: " + id);
        }

        return logs;
    }



    public LogAtribuicaoTarefa salvar(LogAtribuicaoTarefa log) {
        if (log == null) {
            throw new IllegalArgumentException("Log não pode ser nulo");
        }
        return logAtribuicaoTarefaRepository.save(log);
    }

    public LogAtribuicaoTarefa atualizar(LogAtribuicaoTarefa log) {
        if (log == null) {
            throw new IllegalArgumentException("Log não pode ser nulo");
        }
        if (log.getId() == null || !logAtribuicaoTarefaRepository.existsById(log.getId())) {
            throw new EntityNotFoundException("Log não encontrado com ID: " + log.getId());
        }
        return logAtribuicaoTarefaRepository.save(log);
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (!logAtribuicaoTarefaRepository.existsById(id)) {
            throw new EntityNotFoundException("Log não encontrado com ID: " + id);
        }
        logAtribuicaoTarefaRepository.deleteById(id);
    }

    public LogAtribuicaoTarefaDTO adicionarLog(LogAtribuicaoTarefaDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Log não pode ser nulo");
        }

        Tarefa tarefa = tarefaRepository.findById(dto.getIdTarefa())
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada com ID: " + dto.getIdTarefa()));

        LogAtribuicaoTarefa log = LogAtribuicaoTarefa.builder()
                .tarefa(tarefa)
                .idUsuarioAtuante(dto.getIdUsuarioAtribuido())
                .dataRealocacao(dto.getDataRealocacao())
                .observacao(dto.getObservacao())
                .build();

        LogAtribuicaoTarefa salvo = salvar(log);
        dto.setId(salvo.getId());
        return dto;
    }
}
