package com.kronosapisql.service;

import com.kronosapisql.dto.LogAtribuicaoTarefaDTO;
import com.kronosapisql.model.LogAtribuicaoTarefa;
import com.kronosapisql.model.Tarefa;
import com.kronosapisql.model.Usuario;
import com.kronosapisql.repository.LogAtribuicaoTarefaRepository;
import com.kronosapisql.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogAtribuicaoTarefaService {
    private final LogAtribuicaoTarefaRepository logAtribuicaoTarefaRepository;
    private final TarefaRepository tarefaRepository;
    public LogAtribuicaoTarefaService(LogAtribuicaoTarefaRepository logAtribuicaoTarefaRepository, TarefaRepository tarefaRepository) {
        this.logAtribuicaoTarefaRepository = logAtribuicaoTarefaRepository;
        this.tarefaRepository = tarefaRepository;
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
    public LogAtribuicaoTarefaDTO adicionarLog(LogAtribuicaoTarefaDTO dto) {
        Optional<Tarefa> tarefaOpt = tarefaRepository.findById(dto.getIdTarefa());
        if (tarefaOpt.isEmpty()) {
            throw new RuntimeException("Tarefa não encontrada com ID: " + dto.getIdTarefa());
        }

        LogAtribuicaoTarefa log = LogAtribuicaoTarefa.builder()
                .idTarefa(tarefaOpt.get())
                .idUsuarioAtribuido(dto.getIdUsuarioAtribuido())
                .dataRealocacao(dto.getDataRealocacao())
                .observacao(dto.getObservacao())
                .build();

        LogAtribuicaoTarefa salvo = salvar(log);

        dto.setId(salvo.getId());
        return dto;
    }

    public void deletar(Long id) {
        this.logAtribuicaoTarefaRepository.deleteById(id);
    }
    public void atualizar(LogAtribuicaoTarefa logAtribuicaoTarefa) {
        this.logAtribuicaoTarefaRepository.save(logAtribuicaoTarefa);
    }
}
