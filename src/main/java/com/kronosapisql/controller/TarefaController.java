package com.kronosapisql.controller;

import com.kronosapisql.controller.docs.TarefaControllerDocs;
import com.kronosapisql.dto.StatusUpdateDTO;
import com.kronosapisql.dto.TarefaFunctionDTO;
import com.kronosapisql.dto.TarefaRequestDTO;
import com.kronosapisql.model.Tarefa;
import com.kronosapisql.service.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TarefaController implements TarefaControllerDocs {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @Override
    public List<TarefaFunctionDTO> listarTarefasUsuario(Long usuarioId, String tipoTarefa, String status) {
        return tarefaService.listarTarefasUsuario(usuarioId, tipoTarefa, status);
    }

    @Override
    public List<TarefaFunctionDTO> listarTarefasUsuarioGestor(Long idGestor, String tipoTarefa, String status) {
        return tarefaService.listarTarefasUsuarioGestor(idGestor, tipoTarefa, status);
    }

    @Override
    public ResponseEntity<Tarefa> buscarPorId(Long id) {
        Tarefa tarefa = tarefaService.buscarPorId(id);
        return ResponseEntity.ok(tarefa);
    }

    @Override
    public ResponseEntity<Tarefa> inserirTarefa(TarefaRequestDTO dto) {
        Tarefa salvo = tarefaService.salvar(dto);
        return ResponseEntity.status(201).body(salvo);
    }

    @Override
    public ResponseEntity<String> atualizarTarefa(Tarefa tarefa) {
        tarefaService.atualizar(tarefa);
        return ResponseEntity.ok("Tarefa atualizada com sucesso.");
    }

    @Override
    public ResponseEntity<String> atualizarStatus(Long id, StatusUpdateDTO dto) {
        tarefaService.atualizarStatus(id, dto.getStatus());
        return ResponseEntity.ok("Status da tarefa atualizado com sucesso.");
    }

    @Override
    public ResponseEntity<Void> deletarTarefa(Long id) {
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
