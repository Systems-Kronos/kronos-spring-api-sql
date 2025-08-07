package com.kronosapisql.controller;

import com.kronosapisql.model.Empresa;
import com.kronosapisql.service.EmpresaService;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/empresa")
public class EmpresaController  {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/selecionar")
    public List<Empresa> selecionar() {
        return empresaService.selecionar();
    }

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionar(@RequestBody @Valid Empresa empresa) {
        empresaService.salvar(empresa);
        return ResponseEntity.ok("Empresa adicionada com sucesso");
    }

    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@RequestBody @Valid Empresa empresa) {
        empresaService.atualizar(empresa);
        return ResponseEntity.ok("Empresa atualizada com sucesso");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {

        empresaService.deletar(id);
        return ResponseEntity.ok("Empresa removida com sucesso");
    }
}
