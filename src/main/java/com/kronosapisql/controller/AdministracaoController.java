package com.kronosapisql.controller;

import com.kronosapisql.controller.docs.AdministracaoControllerDocs;
import com.kronosapisql.dto.LoginAdmRequestDTO;
import com.kronosapisql.dto.LoginAdmResponseDTO;
import com.kronosapisql.model.Administracao;
import com.kronosapisql.security.JwtUtil;
import com.kronosapisql.service.AdministracaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class AdministracaoController implements AdministracaoControllerDocs {

    private final AdministracaoService administracaoService;
    private final JwtUtil jwtUtil;

    public AdministracaoController(AdministracaoService administracaoService, JwtUtil jwtUtil) {
        this.administracaoService = administracaoService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public List<Administracao> listarAdministradores() {
        return administracaoService.listar();
    }

    @Override
    public ResponseEntity<Administracao> buscarPorId(Long id) {
        return ResponseEntity.ok(administracaoService.buscarPorId(id));
    }

    @Override
    public ResponseEntity<Administracao> buscarPorEmail(String email) {
        return ResponseEntity.ok(administracaoService.buscarPorEmail(email));
    }

    @Override
    public ResponseEntity<Map<String, Object>> adicionarUsuario(@Valid Administracao administracao) {
        Long id = administracaoService.criarAdministracao(administracao);
        Map<String, Object> response = Map.of(
                "mensagem", "Administrador adicionado com sucesso",
                "id", id
        );
        return ResponseEntity.status(201).body(response);
    }

    @Override
    public ResponseEntity<Administracao> atualizar(Long id, Map<String, Object> campos) {
        Administracao atualizado = administracaoService.atualizar(id, campos);
        return ResponseEntity.ok(atualizado);
    }

    @Override
    public ResponseEntity<String> deletarUsuario(Long id) {
        administracaoService.deletar(id);
        return ResponseEntity.ok("Administrador deletado com sucesso.");
    }

    @Override
    public ResponseEntity<LoginAdmResponseDTO> loginAreaRestrita(@Valid LoginAdmRequestDTO loginAdmRequestDTO) {
        Administracao administracao = administracaoService.loginAreaRestrita(
                loginAdmRequestDTO.getEmail(), loginAdmRequestDTO.getSenha()
        );
        String token = jwtUtil.gerarToken(String.valueOf(administracao.getId()));
        return ResponseEntity.ok(new LoginAdmResponseDTO(administracao.getId(), token));
    }
}
