package com.kronosapisql.service;
import com.kronosapisql.dto.TarefaRequestDTO;
import com.kronosapisql.model.Habilidade;
import com.kronosapisql.model.Tarefa;
import com.kronosapisql.model.TarefaHabilidade;
import com.kronosapisql.model.Usuario;
import com.kronosapisql.repository.HabilidadeRepository;
import com.kronosapisql.repository.TarefaRepository;
import com.kronosapisql.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;
    private final HabilidadeRepository habilidadeRepository;

    public TarefaService(TarefaRepository tarefaRepository, UsuarioRepository usuarioRepository, HabilidadeRepository habilidadeRepository) {
        this.usuarioRepository  = usuarioRepository;
        this.habilidadeRepository = habilidadeRepository;
        this.tarefaRepository = tarefaRepository;
    }

    public List<Tarefa> listarTodasTarefas() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> buscarPorId(String id) {
        return tarefaRepository.findById(id);
    }

    public Tarefa salvar(TarefaRequestDTO dto) {


        Usuario usuario = usuarioRepository.findById(dto.getIdUsuarioRelator())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Tarefa tarefa = Tarefa.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .usuarioRelator(usuario)
                .gravidade(dto.getGravidade())
                .urgencia(dto.getUrgencia())
                .tendencia(dto.getTendencia())
                .tempoEstimado(dto.getTempoEstimado())
                .build();

        List<TarefaHabilidade> habilidades = dto.getHabilidades().stream().map(hDTO -> {
            Habilidade habilidade = habilidadeRepository.findById(hDTO.getIdHabilidade())
                    .orElseThrow(() -> new RuntimeException("Habilidade não encontrada"));
            return TarefaHabilidade.builder()
                    .tarefa(tarefa)
                    .habilidade(habilidade)
                    .prioridade(hDTO.getPrioridade())
                    .build();
        }).toList();

        tarefa.setHabilidades(habilidades);

        System.out.println(tarefa);

        return tarefaRepository.save(tarefa);
    }

    public void atualizar(Tarefa tarefa) {
        this.tarefaRepository.save(tarefa);
    }


    public void deletar(String id) {
        tarefaRepository.deleteById(id);
    }
}
