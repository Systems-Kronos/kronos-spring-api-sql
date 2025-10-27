package com.kronosapisql.controller;

import com.kronosapisql.controller.docs.HabilidadeUsuarioControllerDocs;
import com.kronosapisql.dto.HabilidadeUsuarioDTO;
import com.kronosapisql.model.HabilidadeUsuario;
import com.kronosapisql.service.HabilidadeUsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;

@RestController
public class HabilidadeUsuarioController implements HabilidadeUsuarioControllerDocs {

    private final HabilidadeUsuarioService habilidadeUsuarioService;

    public HabilidadeUsuarioController(HabilidadeUsuarioService habilidadeUsuarioService) {
        this.habilidadeUsuarioService = habilidadeUsuarioService;
    }

    @Override
    public ResponseEntity<List<HabilidadeUsuario>> listarTodos() {
        return ResponseEntity.ok(habilidadeUsuarioService.listar());
    }

    @Override
    public ResponseEntity<List<HabilidadeUsuario>> listarPorUsuario(Long id) {
        return ResponseEntity.ok(habilidadeUsuarioService.buscarPorUsuario(id));
    }

    @Override
    public ResponseEntity<List<HabilidadeUsuario>> inserir(@Valid HabilidadeUsuarioDTO dto) {
        List<HabilidadeUsuario> inseridas = habilidadeUsuarioService.inserir(dto.getIdUsuario(), dto.getIdsHabilidade());
        return ResponseEntity.ok(inseridas);
    }

    @Override
    public ResponseEntity<String> atualizarHabilidadeUsuario(@Valid HabilidadeUsuario habilidadeUsuario) {
        habilidadeUsuarioService.atualizar(habilidadeUsuario);
        return ResponseEntity.ok("Habilidade do usuario atualizada com sucesso.");
    }

    @Override
    public ResponseEntity<String> deletarHabilidadeUsuario(long id) {
        habilidadeUsuarioService.deletar(id);
        return ResponseEntity.ok("Habilidade do usuario deletada com sucesso.");
    }
}
