package com.kronosapisql.controller;

import com.kronosapisql.model.Setor;
import com.kronosapisql.service.SetorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/setor")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Setor", description = "Operações relacionadas ao setor")
public class SetorController {
    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @Operation(summary = "Lista todos os setores")
    @GetMapping("/listar")
    public List<Setor> listarSetor() {
        return setorService.listar();
    }

    @GetMapping("/selecionar/{id}")
    @Operation(summary = "Lista o setor pelo id")
    public ResponseEntity<Setor> buscarPorId(@PathVariable Long id) {
        Setor setor = setorService.buscarPorId(id);
        return ResponseEntity.ok(setor);
    }

    @Operation(summary = "Lista todos os setores pertencentes a uma empresa pelo id dela")
    @GetMapping("/selecionar/empresa/{id}")
    public List<Setor> buscarPorEmpresaId(@PathVariable Long id) {
        return setorService.buscarPeloIdEmpresa(id);
    }

    @Operation(summary = "Adiciona um setor")
    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarSetor(@RequestBody @Valid Setor setor) {
        setorService.salvar(setor);
        return ResponseEntity.ok("Setor adicionado com sucesso");
    }

    @Operation(summary = "Atualiza um setor")
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizarSetor(@RequestBody @Valid Setor setor) {
        setorService.atualizar(setor);
        return ResponseEntity.ok("Setor atualizado com sucesso");
    }

    @Operation(summary = "Deleta um setor")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarSetor(@PathVariable long id) {
        setorService.deletar(id);
        return ResponseEntity.ok("Setor removido com sucesso");
    }
}
