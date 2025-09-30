package com.kronosapisql.controller;

import com.kronosapisql.model.Empresa;
import com.kronosapisql.service.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/empresa")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Empresa", description = "Operações relacionadas à empresa")
public class EmpresaController  {
    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @Operation(summary = "Lista todas as empresas")
    @GetMapping("/listar")
    public List<Empresa> listarEmpresa() {
        return empresaService.listar();
    }

    @GetMapping("/selecionar/{id}")
    @Operation(summary = "Lista uma empresa pelo id")
    public ResponseEntity<Empresa> selecionarPeloId(@PathVariable Long id) {
        Empresa empresa = empresaService.buscarPorId(id);
        return ResponseEntity.ok(empresa);
    }

    @Operation(summary = "Adiciona uma nova empresa")
    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarEmpresa(@RequestBody @Valid Empresa empresa) {
        empresaService.salvar(empresa);
        return ResponseEntity.ok("Empresa adicionada com sucesso");
    }

    @Operation(summary = "Atualiza uma empresa")
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizarEmpresa(@RequestBody @Valid Empresa empresa) {
        empresaService.atualizar(empresa);
        return ResponseEntity.ok("Empresa atualizada com sucesso");
    }

    @Operation(summary = "Deleta uma empresa")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarEmpresa(@PathVariable Long id) {
        empresaService.deletar(id);
        return ResponseEntity.ok("Empresa removida com sucesso");
    }
}
