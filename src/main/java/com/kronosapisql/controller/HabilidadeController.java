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

@RestController
@RequestMapping("/api/habilidade")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Habilidade", description = "Operações relacionadas à habilidade")
public class HabilidadeController {
    private final HabilidadeService habilidadeService;

    public HabilidadeController(HabilidadeService habilidadeService) {
        this.habilidadeService = habilidadeService;
    }

    @Operation(summary = "Lista todas as habilidades")
    @GetMapping("/listar")
    public List<Habilidade> listarHabilidade() {
        return habilidadeService.listar();
    }

    @GetMapping("/selecionar/{id}")
    @Operation(summary = "Lista a habilidade pelo id")
    public ResponseEntity<Habilidade> selecionarPeloId(@PathVariable Long id) {
        Habilidade habilidade = habilidadeService.buscarPorId(id);
        return ResponseEntity.ok(habilidade);
    }

    @Operation(summary = "Lista todas as habilidades pertencentes a uma empresa pelo id dela")
    @GetMapping("/selecionar/empresa/{id}")
    public List<Habilidade> buscarPorEmpresaId(@PathVariable Long id) {
        return habilidadeService.buscarPeloIdEmpresa(id);
    }


    @Operation(summary = "Adiciona uma habilidade")
    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarHabilidade(@RequestBody @Valid Habilidade habilidade) {
        habilidadeService.salvar(habilidade);
        return ResponseEntity.ok("Habilidade adicionada com sucesso");
    }

    @Operation(summary = "Atualiza uma habilidade")
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizarHabilidade(@RequestBody @Valid Habilidade habilidade) {
        habilidadeService.atualizar(habilidade);
        return ResponseEntity.ok("Habilidade atualizada com sucesso");
    }

    @Operation(summary = "Deleta uma habilidade")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarHabilidade(@PathVariable long id) {
        habilidadeService.deletar(id);
        return ResponseEntity.ok("Habilidade removida com sucesso");
    }
}
