package com.kronosapisql.service;

import com.kronosapisql.dto.TarefaFunctionDTO;
import com.kronosapisql.dto.TarefaRequestDTO;
import com.kronosapisql.model.*;
import com.kronosapisql.repository.HabilidadeRepository;
import com.kronosapisql.repository.TarefaRepository;
import com.kronosapisql.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;
    private final HabilidadeRepository habilidadeRepository;

    public TarefaService(TarefaRepository tarefaRepository, UsuarioRepository usuarioRepository, HabilidadeRepository habilidadeRepository) {
        this.usuarioRepository = usuarioRepository;
        this.habilidadeRepository = habilidadeRepository;
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID da tarefa não pode ser nulo");
        }
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada com ID: " + id));
    }

    public List<TarefaFunctionDTO> listarTarefasUsuario(Long usuario, String tipoTarefa, String status) {
        return tarefaRepository.listarTarefasUsuarioRaw(usuario, tipoTarefa, status)
                .stream()
                .map(TarefaFunctionDTO::fromRowFunctionIdUsuario)
                .toList();
    }

    public List<TarefaFunctionDTO> listarTarefasUsuarioGestor(Long idGestor, String tipoTarefa, String status) {
        return tarefaRepository.listarTarefasUsuarioGestorRaw(idGestor, tipoTarefa, status)
                .stream()
                .map(TarefaFunctionDTO::fromRowFunctionIdGestor)
                .toList();
    }

    public Tarefa salvar(TarefaRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Dados da tarefa não podem ser nulos");
        }

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuarioRelator())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + dto.getIdUsuarioRelator()));

        Tarefa tarefa = dto.toEntity(usuario);

        List<TarefaHabilidade> habilidades = dto.getHabilidades().stream()
                .map(hDTO -> {
                    Habilidade habilidade = habilidadeRepository.findById(hDTO.getIdHabilidade())
                            .orElseThrow(() -> new EntityNotFoundException("Habilidade não encontrada com ID: " + hDTO.getIdHabilidade()));
                    return TarefaHabilidade.builder()
                            .tarefa(tarefa)
                            .habilidade(habilidade)
                            .prioridade(hDTO.getPrioridade())
                            .build();
                }).toList();

        List<TarefaUsuario> usuariosResponsaveis = dto.getUsuariosResponsaveis().stream()
                .map(idUsuario -> {
                    Usuario usuarioResp = usuarioRepository.findById(idUsuario.longValue())
                            .orElseThrow(() -> new EntityNotFoundException("Usuário responsável não encontrado com ID: " + idUsuario));
                    return TarefaUsuario.builder()
                            .usuarioOriginal(usuarioResp)
                            .usuarioAtuante(usuarioResp)
                            .tarefa(tarefa)
                            .build();
                }).toList();

        tarefa.setUsuariosResponsaveis(usuariosResponsaveis);
        tarefa.setHabilidades(habilidades);
        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizar(Tarefa tarefa) {
        if (tarefa == null) {
            throw new IllegalArgumentException("Tarefa não pode ser nula");
        }
        if (!tarefaRepository.existsById(tarefa.getId())) {
            throw new EntityNotFoundException("Tarefa não encontrada com ID: " + tarefa.getId());
        }
        return tarefaRepository.save(tarefa);
    }

    public void atualizarStatus(Long id, String status) {
        if (id == null || status == null || status.isBlank()) {
            throw new IllegalArgumentException("Id e status não podem ser nulos!");
        }
        tarefaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada com ID: " + id));
        tarefaRepository.atualizarStatusNative(id, status);
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID da tarefa não pode ser nulo");
        }
        if (!tarefaRepository.existsById(id)) {
            throw new EntityNotFoundException("Tarefa não encontrada com ID: " + id);
        }
        tarefaRepository.deleteById(id);
    }
}
