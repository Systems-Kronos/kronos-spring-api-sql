package com.kronosapisql.controller;

import com.kronosapisql.model.Setor;
import com.kronosapisql.service.SetorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/setor")
public class SetorController {
    private final SetorService setorService;
    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }
    @GetMapping("/selecionar")
    public List<Setor> selecionar() {
        return setorService.selecionar();
    }
    @GetMapping("/selecionar/id/{id}")
    public Optional<Setor> selecionarPeloId(@PathVariable Long id) {
        return setorService.selecionarPeloId(id);
    }
    @GetMapping("/selecionar/empresa-id/{id}")
    public List<Setor> selecionarPelaEmpresaId(@PathVariable Long id) {
        return setorService.selecionarPelaEmpresaId(id);
    }
    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionar(@RequestBody @Valid Setor setor) {
        setorService.salvar(setor);
        return ResponseEntity.ok("Setor adicionado com sucesso");
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable long id) {
        setorService.deletar(id);
        return ResponseEntity.ok("Setor removido com sucesso");
    }
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@RequestBody @Valid Setor setor) {
        setorService.atualizar(setor);
        return ResponseEntity.ok("Setor atualizado com sucesso");
    }

}
