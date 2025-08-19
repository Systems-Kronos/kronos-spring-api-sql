package com.kronosapisql.controller;

import com.kronosapisql.model.Mensagem;
import com.kronosapisql.service.MensagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mensagem")
@Tag(name = "Mensagem", description = "Operações relacionadas ao mensagem")
public class MensagemController {
    private final MensagemService mensagemService;

    public MensagemController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    @GetMapping("/selecionar/id/{id}")
    @Operation(summary = "Lista a mensagem pelo id")
    public Optional<Mensagem> selecionarPeloId(@PathVariable Long id) {
        return mensagemService.selecionarPeloId(id);
    }

    @Operation(summary = "Adiciona uma nova mensagem")
    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionar(@Valid @RequestBody Mensagem mensagem) {
        mensagemService.salvar(mensagem);
        return ResponseEntity.ok("Mensagem adicionada com sucesso.");
    }

    @Operation(summary = "Atualiza uma mensagem")
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@Valid @RequestBody Mensagem mensagem) {
        mensagemService.atualizar(mensagem);
        return ResponseEntity.ok("Mensagem atualizada com sucesso.");
    }

    @Operation(summary = "Deleta uma mensagem")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable long id) {
        mensagemService.deletar(id);
        return ResponseEntity.ok("Mensagem deletada com sucesso.");
    }
}
