package com.kronosapisql.controller;

import com.kronosapisql.dto.LoginDTO;
import com.kronosapisql.security.JwtUtil;
import com.kronosapisql.model.Usuario;
import com.kronosapisql.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
@Tag(name = "Usuario", description = "Operações relacionadas ao usuário")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;

    public UsuarioController(UsuarioService usuarioService, JwtUtil jwtUtil){
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "Lista todos os usuários")
    @GetMapping("/selecionar")
    public List<Usuario> selecionar() {return usuarioService.selecionar();}

    @GetMapping("/selecionarId/{id}")
    @Operation(summary = "Lista um usuário pelo id")
    public Optional<Usuario> selecionarPeloId(@PathVariable Long id) {
        return usuarioService.selecionarPeloId(id);
    }

    @GetMapping("/selecionarCpf/{cpf}")
    @Operation(summary = "Lista um usuário pelo cpf")
    public Optional<Usuario> selecionarPeloCpf(@PathVariable String cpf) {
        return usuarioService.selecionarPeloCpf(cpf);
    }

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
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.ok("Usuário deletado com sucesso.");
    }

    @Operation(summary = "Faz login de um usuário")
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        Optional<Usuario> usuarioEncontrado = usuarioService.login(loginDTO.getCpf(), loginDTO.getSenha());
        if (usuarioEncontrado.isPresent()) {
            String token = jwtUtil.gerarToken(String.valueOf(usuarioEncontrado.get().getId()));
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(401).body("Usuário ou senha inválidos");
        }
    }
}
