package com.kronosapisql.service;
import com.kronosapisql.model.Tarefa;
import com.kronosapisql.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public List<Tarefa> listarTodasTarefas() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> buscarPorId(String id) {
        return tarefaRepository.findById(id);
    }

    public Tarefa salvar(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public void atualizar(Tarefa tarefa) {
        this.tarefaRepository.save(tarefa);
    }


    public void deletar(String id) {
        tarefaRepository.deleteById(id);
    }
}
