package com.kronosapisql.controller;

import com.kronosapisql.controller.docs.MensagemControllerDocs;
import com.kronosapisql.model.Mensagem;
import com.kronosapisql.service.MensagemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MensagemController implements MensagemControllerDocs {

    private final MensagemService mensagemService;

    public MensagemController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    @Override
    public ResponseEntity<Mensagem> selecionarPeloId(Long id) {
        return ResponseEntity.ok(mensagemService.buscarPorId(id));
    }

    @Override
    public ResponseEntity<String> adicionarMensagem(Mensagem mensagem) {
        mensagemService.salvar(mensagem);
        return ResponseEntity.ok("Mensagem adicionada com sucesso.");
    }

    @Override
    public ResponseEntity<String> atualizarMensagem(Mensagem mensagem) {
        mensagemService.atualizar(mensagem);
        return ResponseEntity.ok("Mensagem atualizada com sucesso.");
    }

    @Override
    public ResponseEntity<String> deletarMensagem(long id) {
        mensagemService.deletar(id);
        return ResponseEntity.ok("Mensagem deletada com sucesso.");
    }
}
