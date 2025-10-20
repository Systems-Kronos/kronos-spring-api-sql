package com.kronosapisql.controller;

import com.kronosapisql.dto.*;
import com.kronosapisql.security.JwtUtil;
import com.kronosapisql.model.Usuario;
import com.kronosapisql.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.boot.web.reactive.context.ReactiveWebServerApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuario")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Usuario", description = "Operações relacionadas ao usuário")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;

    public UsuarioController(UsuarioService usuarioService, JwtUtil jwtUtil){
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "Lista todos os usuários")
    @GetMapping("/listar")
    public List<Usuario> listarUsuario() {
        return usuarioService.listar();
    }

    @Operation(summary = "Lista todos os usuários de um gestor específico baseado na function")
    @GetMapping("/selecionarFunction/{idGestor}")
    public List<UsuarioFunctionDTO> listarFuncionariosGestor(@PathVariable Long idGestor) {
        return usuarioService.listarFuncionariosGestor(idGestor);
    }

    @GetMapping("/selecionarId/{id}")
    @Operation(summary = "Lista um usuário pelo id")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/selecionarCpf/{cpf}")
    @Operation(summary = "Lista um usuário pelo cpf")
    public ResponseEntity<Usuario> buscarPorCpf(@PathVariable String cpf) {
        Usuario usuario = usuarioService.buscarPorCpf(cpf);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/selecionarNoSec/{cpf}")
    @Operation(summary = "Lista o telefone de um usuário pelo cpf")
    public ResponseEntity<UsuarioTelefoneDTO> buscarTelefonePorCpf(@PathVariable String cpf) {
        UsuarioTelefoneDTO dto = usuarioService.buscarTelefonePorCpf(cpf);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Adiciona um novo usuário")
    @PostMapping("/adicionar")
    public ResponseEntity<Map<String, Object>> adicionarUsuario(@Valid @RequestBody UsuarioDTO usuariodto) {
        Long idUsuario = usuarioService.criarUsuario(usuariodto);

        Map<String, Object> response = Map.of(
                "mensagem", "Usuário adicionado com sucesso",
                "id", idUsuario
        );

        return ResponseEntity.status(201).body(response);
    }

    @Operation(summary = "Atualiza um usuário")
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizarUsuario(@Valid @RequestBody Usuario usuario) {
        usuarioService.atualizar(usuario);
        return ResponseEntity.ok("Usuário atualizada com sucesso.");
    }

    @Operation(summary = "Atualiza alguns campos de usuário")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Usuario> atualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> campos) {

        Usuario atualizado = usuarioService.atualizarParcial(id, campos);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Deleta um usuário")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.ok("Usuário deletado com sucesso.");
    }

    @Operation(summary = "Faz login de um usuário no App")
    @PostMapping("/loginApp")
    public ResponseEntity<LoginResponseDTO> loginApp(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        Usuario usuario = usuarioService.loginApp(loginRequestDTO.getCpf(), loginRequestDTO.getSenha());

        String token = jwtUtil.gerarToken(String.valueOf(usuario.getId()));
        return ResponseEntity.ok(new LoginResponseDTO(usuario.getId(), token));
    }

    @Operation(summary = "Faz login de um gestor na Plataforma")
    @PostMapping("/loginPlataforma")
    public ResponseEntity<LoginResponseDTO> loginPlataforma(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        Usuario usuario = usuarioService.loginPlataforma(loginRequestDTO.getCpf(), loginRequestDTO.getSenha());

        String token = jwtUtil.gerarToken(String.valueOf(usuario.getId()));
        return ResponseEntity.ok(new LoginResponseDTO(usuario.getId(), token));
    }
}
