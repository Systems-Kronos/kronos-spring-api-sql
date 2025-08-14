package com.kronosapisql.controller;

import com.kronosapisql.model.Usuario;
import com.kronosapisql.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@Tag(name = "Usuario", description = "Operações relacionadas ao usuário")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Lista todos os usuários")
    @GetMapping("/selecionar")
    public List<Usuario> selecionar() {return usuarioService.selecionar();}

    @Operation(summary = "Adiciona um novo usuário")
    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionar(@Valid @RequestBody Usuario usuario) {
        usuarioService.salvar(usuario);
        return ResponseEntity.ok("Usuário adicionado com sucesso.");
    }

    @Operation(summary = "Atualiza um usuário")
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@Valid @RequestBody Usuario usuario) {
        usuarioService.atualizar(usuario);
        return ResponseEntity.ok("Usuário atualizada com sucesso.");
    }

    @Operation(summary = "Deleta um usuário")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable long id) {
        usuarioService.deletar(id);
        return ResponseEntity.ok("Usuário deletado com sucesso.");
    }
}
