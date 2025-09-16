package com.kronosapisql.service;
import com.kronosapisql.dto.TarefaRequestDTO;
import com.kronosapisql.model.*;
import com.kronosapisql.repository.HabilidadeRepository;
import com.kronosapisql.repository.TarefaRepository;
import com.kronosapisql.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        try {
            return tarefaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar tarefas: " + e.getMessage());
        }
    }

    public Optional<Tarefa> buscarPorId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID da tarefa não pode ser nulo ou vazio");
        }
        try {
            Optional<Tarefa> tarefa = tarefaRepository.findById(id);
            if (!tarefa.isPresent()) {
                throw new EntityNotFoundException("Tarefa não encontrada com ID: " + id);
            }
            return tarefa;
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar tarefa: " + e.getMessage());
        }
    }

    public Tarefa salvar(TarefaRequestDTO dto) {
        try {
            if (dto == null) {
                throw new IllegalArgumentException("Dados da tarefa não podem ser nulos");
            }

            Usuario usuario = usuarioRepository.findById(dto.getIdUsuarioRelator())
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + dto.getIdUsuarioRelator()));

            Tarefa tarefa = Tarefa.builder()
                    .nome(dto.getNome())
                    .descricao(dto.getDescricao())
                    .usuarioRelator(usuario)
                    .gravidade(dto.getGravidade())
                    .urgencia(dto.getUrgencia())
                    .tendencia(dto.getTendencia())
                    .tempoEstimado(dto.getTempoEstimado())
                    .dataAtribuicao(dto.getDataAtribuicao())
                    .dataConclusao(null)
                    .status(dto.getStatus())
                    .build();

            List<TarefaHabilidade> habilidades = dto.getHabilidades().stream().map(hDTO -> {
                Habilidade habilidade = habilidadeRepository.findById(hDTO.getIdHabilidade())
                        .orElseThrow(() -> new EntityNotFoundException("Habilidade não encontrada com ID: " + hDTO.getIdHabilidade()));
                return TarefaHabilidade.builder()
                        .tarefa(tarefa)
                        .habilidade(habilidade)
                        .prioridade(hDTO.getPrioridade())
                        .build();
            }).collect(java.util.stream.Collectors.toList());

            List<TarefaUsuario> usuariosResponsaveis = dto.getUsuariosResponsaveis().stream().map(idUsuario -> {
                Usuario usuarioResponsavel = usuarioRepository.findById(idUsuario.longValue())
                        .orElseThrow(() -> new EntityNotFoundException("Usuário responsável não encontrado com ID: " + idUsuario));
                return TarefaUsuario.builder()
                        .usuarioOriginal(usuarioResponsavel)
                        .usuarioAtuante(usuarioResponsavel)
                        .tarefa(tarefa)
                        .build();
            }).collect(java.util.stream.Collectors.toList());

            tarefa.setUsuariosResponsaveis(usuariosResponsaveis);
            tarefa.setHabilidades(habilidades);

            return tarefaRepository.save(tarefa);
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar tarefa: " + e.getMessage());
        }
    }

    public void atualizar(Tarefa tarefa) {
        if (tarefa == null) {
            throw new IllegalArgumentException("Tarefa não pode ser nula");
        }
        try {
            if (!tarefaRepository.existsById(String.valueOf(tarefa.getId()))) {
                throw new EntityNotFoundException("Tarefa não encontrada com ID: " + tarefa.getId());
            }
            tarefaRepository.save(tarefa);
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar tarefa: " + e.getMessage());
        }
    }

    public void deletar(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID da tarefa não pode ser nulo ou vazio");
        }
        try {
            tarefaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Tarefa não encontrada com ID: " + id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar tarefa: " + e.getMessage());
        }
    }
}
