package com.kronosapisql.controller;


import com.kronosapisql.model.HabilidadeUsuario;
import com.kronosapisql.service.HabilidadeUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/habilidade-usuario")
@Tag(name = "Habilidade Usuario", description = "Operações relacionadas ao Habilidade Usuario")

public class HabilidadeUsuarioController {

    private final HabilidadeUsuarioService habilidadeUsuarioService;

    public HabilidadeUsuarioController(HabilidadeUsuarioService habilidadeUsuarioService) {
        this.habilidadeUsuarioService = habilidadeUsuarioService;
    }

    @GetMapping("/selecionar/{id}")
    @Operation(summary = "Lista a habilidade do usuario pelo id")
    public Optional<HabilidadeUsuario> selecionarPeloId(@PathVariable Long id) {
        return habilidadeUsuarioService.selecionarPeloId(id);
    }

    @Operation(summary = "Adiciona um nova habilidade do usuario")
    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionar(@Valid @RequestBody HabilidadeUsuario habilidadeUsuario) {
        habilidadeUsuarioService.salvar(habilidadeUsuario);
        return ResponseEntity.ok("Habilidade do usuario adicionada com sucesso.");
    }

    @Operation(summary = "Atualiza uma habilidade do usuario")
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@Valid @RequestBody HabilidadeUsuario habilidadeUsuario) {
        habilidadeUsuarioService.atualizar(habilidadeUsuario);
        return ResponseEntity.ok("Habilidade do usuario atualizada com sucesso.");
    }

    @Operation(summary = "Deleta uma habilidade do usuario")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable long id) {
        habilidadeUsuarioService.deletar(id);
        return ResponseEntity.ok("Habilidade do usuario deletada com sucesso.");
    }
}




