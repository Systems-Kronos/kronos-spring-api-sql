package com.kronosapisql.controller;

import com.kronosapisql.dto.HabilidadeUsuarioDTO;
import com.kronosapisql.model.HabilidadeUsuario;
import com.kronosapisql.service.HabilidadeUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habilidade-usuario")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Habilidade Usuario", description = "Operações relacionadas ao Habilidade Usuario")

public class HabilidadeUsuarioController {
    private final HabilidadeUsuarioService habilidadeUsuarioService;

    public HabilidadeUsuarioController(HabilidadeUsuarioService habilidadeUsuarioService) {
        this.habilidadeUsuarioService = habilidadeUsuarioService;
    }

    @GetMapping("/selecionar/{id}")
    @Operation(summary = "Lista a habilidade do usuario pelo id")
    public ResponseEntity<HabilidadeUsuario> selecionarPeloId(@PathVariable Long id) {
        HabilidadeUsuario habilidadeUsuario = habilidadeUsuarioService.buscarPorId(id);
        return ResponseEntity.ok(habilidadeUsuario);
    }

    @Operation(summary = "Adiciona um nova habilidade do usuario")
    @PostMapping("/adicionar")
    public ResponseEntity<List<HabilidadeUsuario>> inserir(@RequestBody HabilidadeUsuarioDTO dto) {
        List<HabilidadeUsuario> habilidadesInseridas = habilidadeUsuarioService.inserir(dto.getIdUsuario(), dto.getIdsHabilidade());
        return ResponseEntity.ok(habilidadesInseridas);
    }

    @Operation(summary = "Atualiza uma habilidade do usuario")
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizarHabilidadeUsuario(@Valid @RequestBody HabilidadeUsuario habilidadeUsuario) {
        habilidadeUsuarioService.atualizar(habilidadeUsuario);
        return ResponseEntity.ok("Habilidade do usuario atualizada com sucesso.");
    }

    @Operation(summary = "Deleta uma habilidade do usuario")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarHabilidadeUsuario(@PathVariable long id) {
        habilidadeUsuarioService.deletar(id);
        return ResponseEntity.ok("Habilidade do usuario deletada com sucesso.");
    }
}
