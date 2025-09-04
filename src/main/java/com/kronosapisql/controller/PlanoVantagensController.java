package com.kronosapisql.controller;

import com.kronosapisql.model.PlanoVantagens;
import com.kronosapisql.service.PlanoVantagensService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vantagens")
@Tag(name = "Vantagens", description = "Operações relacionadas a vantagens dos planos")
public class PlanoVantagensController {
    private final PlanoVantagensService planoVantagensService;

    public PlanoVantagensController(PlanoVantagensService planoVantagensService) {
        this.planoVantagensService = planoVantagensService;
    }

    @GetMapping("/selecionar/")
    @Operation(summary = "Lista todos as vantagens")
    public List<PlanoVantagens> selecionar() {
        return planoVantagensService.selecionar();
    }

    @GetMapping("/selecionar/{id}")
    @Operation(summary = "Lista uma vantagem pelo id")
    public Optional<PlanoVantagens> selecionarPeloId(@PathVariable Long id) {
        return planoVantagensService.selecionarPeloId(id);
    }

    @Operation(summary = "Adiciona uma vantagem")
    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionar(@RequestBody @Valid PlanoVantagens planoVantagens) {
        planoVantagensService.salvar(planoVantagens);
        return ResponseEntity.ok("Vantagem adicionada com sucesso");
    }

    @Operation(summary = "Atualiza uma vantagem")
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@Valid @RequestBody PlanoVantagens planoVantagens) {
        planoVantagensService.atualizar(planoVantagens);
        return ResponseEntity.ok("Vantagem atualizada com sucesso.");
    }

    @Operation(summary = "Deleta uma vantagem")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable long id) {
        planoVantagensService.deletar(id);
        return ResponseEntity.ok("Vantagem deletada com sucesso.");
    }
}
