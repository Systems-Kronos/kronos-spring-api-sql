package com.kronosapisql.controller;

import com.kronosapisql.model.Empresa;
import com.kronosapisql.service.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empresa")
@Tag(name = "Empresa", description = "Operações relacionadas à empresa")
public class EmpresaController  {
    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @Operation(summary = "Lista todas as empresas")
    @GetMapping("/selecionar")
    public List<Empresa> selecionar() {
        return empresaService.selecionar();
    }

    @GetMapping("/selecionar/{id}")
    @Operation(summary = "Lista uma empresa pelo id")
    public Optional<Empresa> selecionarPeloId(@PathVariable Long id) {
        return empresaService.selecionarPeloId(id);
    }

    @Operation(summary = "Adiciona uma nova empresa")
    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionar(@RequestBody @Valid Empresa empresa) {
        empresaService.salvar(empresa);
        return ResponseEntity.ok("Empresa adicionada com sucesso");
    }

    @Operation(summary = "Atualiza uma empresa")
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@RequestBody @Valid Empresa empresa) {
        empresaService.atualizar(empresa);
        return ResponseEntity.ok("Empresa atualizada com sucesso");
    }

    @Operation(summary = "Deleta uma empresa")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        empresaService.deletar(id);
        return ResponseEntity.ok("Empresa removida com sucesso");
    }
}
