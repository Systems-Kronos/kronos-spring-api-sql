package com.kronosapisql.controller;

import com.kronosapisql.controller.docs.SetorControllerDocs;
import com.kronosapisql.model.Setor;
import com.kronosapisql.service.SetorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SetorController implements SetorControllerDocs {

    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @Override
    public List<Setor> listarSetor() {
        return setorService.listar();
    }

    @Override
    public ResponseEntity<Setor> buscarPorId(Long id) {
        return ResponseEntity.ok(setorService.buscarPorId(id));
    }

    @Override
    public List<Setor> buscarPorEmpresaId(Long id) {
        return setorService.buscarPeloIdEmpresa(id);
    }

    @Override
    public ResponseEntity<String> adicionarSetor(Setor setor) {
        setorService.salvar(setor);
        return ResponseEntity.ok("Setor adicionado com sucesso");
    }

    @Override
    public ResponseEntity<String> atualizarSetor(Setor setor) {
        setorService.atualizar(setor);
        return ResponseEntity.ok("Setor atualizado com sucesso");
    }

    @Override
    public ResponseEntity<String> deletarSetor(long id) {
        setorService.deletar(id);
        return ResponseEntity.ok("Setor removido com sucesso");
    }
}
