package com.kronosapisql.controller;

import com.kronosapisql.controller.docs.UsuarioControllerDocs;
import com.kronosapisql.dto.*;
import com.kronosapisql.model.Usuario;
import com.kronosapisql.security.JwtUtil;
import com.kronosapisql.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UsuarioController implements UsuarioControllerDocs {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;

    public UsuarioController(UsuarioService usuarioService, JwtUtil jwtUtil) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public List<Usuario> listarUsuario() {
        return usuarioService.listar();
    }

    @Override
    public List<UsuarioFunctionDTO> listarFuncionariosGestor(Long idGestor) {
        return usuarioService.listarFuncionariosGestor(idGestor);
    }

    @Override
    public ResponseEntity<Usuario> buscarPorId(Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @Override
    public ResponseEntity<Usuario> buscarPorCpf(String cpf) {
        Usuario usuario = usuarioService.buscarPorCpf(cpf);
        return ResponseEntity.ok(usuario);
    }

    @Override
    public ResponseEntity<UsuarioTelefoneDTO> buscarTelefonePorCpf(String cpf) {
        UsuarioTelefoneDTO dto = usuarioService.buscarTelefonePorCpf(cpf);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Map<String, Object>> adicionarUsuario(@Valid UsuarioDTO usuarioDTO) {
        Long id = usuarioService.criarUsuario(usuarioDTO);
        Map<String, Object> response = Map.of(
                "mensagem", "Usuário adicionado com sucesso",
                "id", id
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<String> atualizarUsuario(@Valid Usuario usuario) {
        usuarioService.atualizar(usuario);
        return ResponseEntity.ok("Usuário atualizado com sucesso.");
    }

    @Override
    public ResponseEntity<String> atualizarSenha(Long id, @Valid SenhaDTO senhaDTO) {
        usuarioService.atualizarSenha(id, senhaDTO.getNovaSenha());
        return ResponseEntity.ok("Senha atualizada com sucesso.");
    }

    @Override
    public ResponseEntity<Usuario> atualizarParcial(Long id, Map<String, Object> campos) {
        Usuario atualizado = usuarioService.atualizarParcial(id, campos);
        return ResponseEntity.ok(atualizado);
    }

    @Override
    public ResponseEntity<String> deletarUsuario(Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.ok("Usuário deletado com sucesso.");
    }

    @Override
    public ResponseEntity<LoginResponseDTO> loginApp(@Valid LoginRequestDTO loginRequestDTO) {
        Usuario usuario = usuarioService.loginApp(loginRequestDTO.getCpf(), loginRequestDTO.getSenha());
        String token = jwtUtil.gerarToken(String.valueOf(usuario.getId()));
        return ResponseEntity.ok(new LoginResponseDTO(usuario.getId(), token));
    }

    @Override
    public ResponseEntity<LoginResponseDTO> loginPlataforma(@Valid LoginRequestDTO loginRequestDTO) {
        Usuario usuario = usuarioService.loginPlataforma(loginRequestDTO.getCpf(), loginRequestDTO.getSenha());
        String token = jwtUtil.gerarToken(String.valueOf(usuario.getId()));
        return ResponseEntity.ok(new LoginResponseDTO(usuario.getId(), token));
    }
}
