package com.kronosapisql.controller;

import com.kronosapisql.controller.docs.HabilidadeControllerDocs;
import com.kronosapisql.model.Habilidade;
import com.kronosapisql.service.HabilidadeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HabilidadeController implements HabilidadeControllerDocs {

    private final HabilidadeService habilidadeService;

    public HabilidadeController(HabilidadeService habilidadeService) {
        this.habilidadeService = habilidadeService;
    }

    @Override
    public List<Habilidade> listarHabilidade() {
        return habilidadeService.listar();
    }

    @Override
    public ResponseEntity<Habilidade> selecionarPeloId(Long id) {
        Habilidade habilidade = habilidadeService.buscarPorId(id);
        return ResponseEntity.ok(habilidade);
    }

    @Override
    public List<Habilidade> buscarPorEmpresaId(Long id) {
        return habilidadeService.buscarPeloIdEmpresa(id);
    }

    @Override
    public ResponseEntity<String> adicionarHabilidade(@Valid Habilidade habilidade) {
        habilidadeService.salvar(habilidade);
        return ResponseEntity.ok("Habilidade adicionada com sucesso");
    }

    @Override
    public ResponseEntity<String> atualizarHabilidade(@Valid Habilidade habilidade) {
        habilidadeService.atualizar(habilidade);
        return ResponseEntity.ok("Habilidade atualizada com sucesso");
    }

    @Override
    public ResponseEntity<String> deletarHabilidade(long id) {
        habilidadeService.deletar(id);
        return ResponseEntity.ok("Habilidade removida com sucesso");
    }
}
