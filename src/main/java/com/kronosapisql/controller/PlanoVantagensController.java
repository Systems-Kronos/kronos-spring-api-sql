package com.kronosapisql.controller;

import com.kronosapisql.controller.docs.PlanoVantagensControllerDocs;
import com.kronosapisql.model.PlanoVantagens;
import com.kronosapisql.service.PlanoVantagensService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanoVantagensController implements PlanoVantagensControllerDocs {

    private final PlanoVantagensService planoVantagensService;

    public PlanoVantagensController(PlanoVantagensService planoVantagensService) {
        this.planoVantagensService = planoVantagensService;
    }

    @Override
    public ResponseEntity<PlanoVantagens> selecionarPeloId(Long id) {
        return ResponseEntity.ok(planoVantagensService.buscarPorId(id));
    }

    @Override
    public ResponseEntity<String> adicionarVantagens(PlanoVantagens planoVantagens) {
        planoVantagensService.salvar(planoVantagens);
        return ResponseEntity.ok("Vantagem adicionada com sucesso");
    }

    @Override
    public ResponseEntity<String> atualizarVantagens(PlanoVantagens planoVantagens) {
        planoVantagensService.atualizar(planoVantagens);
        return ResponseEntity.ok("Vantagem atualizada com sucesso.");
    }

    @Override
    public ResponseEntity<String> deletarVantagens(long id) {
        planoVantagensService.deletar(id);
        return ResponseEntity.ok("Vantagem deletada com sucesso.");
    }
}
