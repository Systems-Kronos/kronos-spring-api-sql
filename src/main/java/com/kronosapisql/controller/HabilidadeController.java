package com.kronosapisql.controller;

import com.kronosapisql.model.Habilidade;
import com.kronosapisql.service.HabilidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/habilidade")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Habilidade", description = "Operações relacionadas à habilidade")
public class HabilidadeController {
    private HabilidadeService habilidadeService;

    public HabilidadeController(HabilidadeService habilidadeService) {
        this.habilidadeService = habilidadeService;
    }

    @Operation(summary = "Lista todas as habilidades")
    @GetMapping("/selecionar")
    public List<Habilidade> selecionar() {
        return habilidadeService.selecionar();
    }

    @GetMapping("/selecionar/{id}")
    @Operation(summary = "Lista a habilidade pelo id")
    public Optional<Habilidade> selecionarPeloId(@PathVariable Long id) {
        return habilidadeService.selecionarPeloId(id);
    }

    @Operation(summary = "Adiciona uma habilidade")
    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionar(@RequestBody @Valid Habilidade habilidade) {
        habilidadeService.salvar(habilidade);
        return ResponseEntity.ok("Habilidade adicionada com sucesso");
    }

    @Operation(summary = "Atualiza uma habilidade")
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@RequestBody @Valid Habilidade habilidade) {
        habilidadeService.atualizar(habilidade);
        return ResponseEntity.ok("Habilidade atualizada com sucesso");
    }

    @Operation(summary = "Deleta uma habilidade")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable long id) {
        habilidadeService.deletar(id);
        return ResponseEntity.ok("Habilidade removida com sucesso");
    }
}
