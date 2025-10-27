package com.kronosapisql.controller.docs;

import com.kronosapisql.dto.*;
import com.kronosapisql.model.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Usuario", description = "Operações relacionadas ao usuário")
@RequestMapping("/api/usuario")
public interface UsuarioControllerDocs {

    @Operation(summary = "Lista todos os usuários")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum usuário encontrado")
    })
    @GetMapping("/listar")
    List<Usuario> listarUsuario();

    @Operation(summary = "Lista todos os usuários de um gestor específico baseada na function")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum usuário encontrado para o gestor informado")
    })
    @GetMapping("/selecionarFunction/{idGestor}")
    List<UsuarioFunctionDTO> listarFuncionariosGestor(@PathVariable Long idGestor);

    @Operation(summary = "Busca um usuário pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/selecionarId/{id}")
    ResponseEntity<Usuario> buscarPorId(@PathVariable Long id);

    @Operation(summary = "Busca um usuário pelo CPF")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/selecionarCpf/{cpf}")
    ResponseEntity<Usuario> buscarPorCpf(@PathVariable String cpf);

    @Operation(summary = "Busca o telefone de um usuário pelo CPF (sem segurança)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Telefone retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/selecionarNoSec/{cpf}")
    ResponseEntity<UsuarioTelefoneDTO> buscarTelefonePorCpf(@PathVariable String cpf);

    @Operation(summary = "Adiciona um novo usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro nos dados informados")
    })
    @PostMapping("/adicionar")
    ResponseEntity<Map<String, Object>> adicionarUsuario(@Valid @RequestBody UsuarioDTO usuariodto);

    @Operation(summary = "Atualiza um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping("/atualizar")
    ResponseEntity<String> atualizarUsuario(@Valid @RequestBody Usuario usuario);

    @Operation(summary = "Atualiza a senha do usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Senha atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping("/atualizarSenha/{id}")
    ResponseEntity<String> atualizarSenha(@PathVariable Long id, @Valid @RequestBody SenhaDTO senhaDTO);

    @Operation(summary = "Atualiza parcialmente os campos de um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado parcialmente com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping("/atualizar/{id}")
    ResponseEntity<Usuario> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos);

    @Operation(summary = "Deleta um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "409", description = "Violação de integridade referencial ao deletar")
    })
    @DeleteMapping("/deletar/{id}")
    ResponseEntity<String> deletarUsuario(@PathVariable Long id);

    @Operation(summary = "Faz login de um usuário no App")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @PostMapping("/loginApp")
    ResponseEntity<LoginResponseDTO> loginApp(@Valid @RequestBody LoginRequestDTO loginRequestDTO);

    @Operation(summary = "Faz login de um gestor na Plataforma")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @PostMapping("/loginPlataforma")
    ResponseEntity<LoginResponseDTO> loginPlataforma(@Valid @RequestBody LoginRequestDTO loginRequestDTO);
}
