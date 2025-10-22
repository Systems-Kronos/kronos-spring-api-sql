package com.kronosapisql.controller;

import com.kronosapisql.controller.docs.LogAtribuicaoTarefaControllerDocs;
import com.kronosapisql.dto.LogAtribuicaoTarefaDTO;
import com.kronosapisql.dto.LogAtribuicaoTarefaResponse;
import com.kronosapisql.model.LogAtribuicaoTarefa;
import com.kronosapisql.service.LogAtribuicaoTarefaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogAtribuicaoTarefaController implements LogAtribuicaoTarefaControllerDocs {

    private final LogAtribuicaoTarefaService logAtribuicaoTarefaService;

    public LogAtribuicaoTarefaController(LogAtribuicaoTarefaService logAtribuicaoTarefaService) {
        this.logAtribuicaoTarefaService = logAtribuicaoTarefaService;
    }

    @Override
    public ResponseEntity<LogAtribuicaoTarefa> selecionarPeloId(Long id) {
        return ResponseEntity.ok(logAtribuicaoTarefaService.buscarPorId(id));
    }

    @Override
    public ResponseEntity<List<LogAtribuicaoTarefaResponse>> buscarPorIdTarefa(Long id) {
        return ResponseEntity.ok(logAtribuicaoTarefaService.buscarPorIdTarefa(id));
    }

    @Override
    public ResponseEntity<LogAtribuicaoTarefaDTO> adicionarLog(LogAtribuicaoTarefaDTO dto) {
        LogAtribuicaoTarefaDTO salvo = logAtribuicaoTarefaService.adicionarLog(dto);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> atualizarLog(LogAtribuicaoTarefa logAtribuicaoTarefa) {
        logAtribuicaoTarefaService.atualizar(logAtribuicaoTarefa);
        return ResponseEntity.ok("Log atualizado com sucesso.");
    }

    @Override
    public ResponseEntity<String> deletarLog(long id) {
        logAtribuicaoTarefaService.deletar(id);
        return ResponseEntity.ok("Log deletado com sucesso.");
    }
}
