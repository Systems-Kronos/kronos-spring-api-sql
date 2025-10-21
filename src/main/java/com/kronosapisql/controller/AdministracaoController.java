package com.kronosapisql.controller;

import com.kronosapisql.dto.*;
import com.kronosapisql.model.Administracao;
import com.kronosapisql.model.Usuario;
import com.kronosapisql.security.JwtUtil;
import com.kronosapisql.service.AdministracaoService;
import com.kronosapisql.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/administracao")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Administração", description = "Operações relacionadas a administração")
public class AdministracaoController {
    private final AdministracaoService administracaoService;
    private final JwtUtil jwtUtil;

    public AdministracaoController(AdministracaoService administracaoService, JwtUtil jwtUtil){
        this.administracaoService = administracaoService;
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "Lista todos os administradores")
    @GetMapping("/listar")
    public List<Administracao> listarAdministradores() {
        return administracaoService.listar();
    }

    @GetMapping("/selecionarId/{id}")
    @Operation(summary = "Lista um administrador pelo id")
    public ResponseEntity<Administracao> buscarPorId(@PathVariable Long id) {
        Administracao administracao = administracaoService.buscarPorId(id);
        return ResponseEntity.ok(administracao);
    }

    @GetMapping("/selecionarEmail/{email}")
    @Operation(summary = "Lista um administrador pelo email")
    public ResponseEntity<Administracao> buscarPorCpf(@PathVariable String email) {
        Administracao administracao = administracaoService.buscarPorEmail(email);
        return ResponseEntity.ok(administracao);
    }

    @Operation(summary = "Adiciona um novo administrador")
    @PostMapping("/adicionar")
    public ResponseEntity<Map<String, Object>> adicionarUsuario(@Valid @RequestBody Administracao administracao) {
        Long idAdministrador = administracaoService.criarAdministracao(administracao);

        Map<String, Object> response = Map.of(
                "mensagem", "Administrador adicionado com sucesso",
                "id", idAdministrador
        );

        return ResponseEntity.status(201).body(response);
    }


    @Operation(summary = "Atualiza alguns campos de administracao")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Administracao> atualizar(
            @PathVariable Long id,
            @RequestBody Map<String, Object> campos) {

        Administracao atualizado = administracaoService.atualizar(id, campos);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Deleta um administrador")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long id) {
        administracaoService.deletar(id);
        return ResponseEntity.ok("Administrador deletado com sucesso.");
    }

    @Operation(summary = "Faz login de um administração na área restrita")
    @PostMapping("/loginArea")
    public ResponseEntity<LoginAdmResponseDTO> loginAreaRestrita(@Valid @RequestBody LoginAdmRequestDTO loginAdmRequestDTO) {
        Administracao administracao = administracaoService.loginAreaRestrita(loginAdmRequestDTO.getEmail(), loginAdmRequestDTO.getSenha());

        String token = jwtUtil.gerarToken(String.valueOf(administracao.getId()));
        return ResponseEntity.ok(new LoginAdmResponseDTO(administracao.getId(), token));
    }
}
