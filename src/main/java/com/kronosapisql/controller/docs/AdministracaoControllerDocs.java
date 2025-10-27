package com.kronosapisql.controller.docs;

import com.kronosapisql.dto.LoginAdmRequestDTO;
import com.kronosapisql.dto.LoginAdmResponseDTO;
import com.kronosapisql.model.Administracao;
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
@Tag(name = "Administração", description = "Operações relacionadas à administração")
@RequestMapping("/api/administracao")
public interface AdministracaoControllerDocs {

    @Operation(summary = "Lista todos os administradores")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de administradores retornada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @GetMapping("/listar")
    List<Administracao> listarAdministradores();

    @Operation(summary = "Lista um administrador pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Administrador encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Administrador não encontrado")
    })
    @GetMapping("/selecionarId/{id}")
    ResponseEntity<Administracao> buscarPorId(@PathVariable Long id);

    @Operation(summary = "Lista um administrador pelo email")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Administrador encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Administrador não encontrado")
    })
    @GetMapping("/selecionarEmail/{email}")
    ResponseEntity<Administracao> buscarPorEmail(@PathVariable String email);

    @Operation(summary = "Adiciona um novo administrador")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Administrador adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/adicionar")
    ResponseEntity<Map<String, Object>> adicionarUsuario(@Valid @RequestBody Administracao administracao);

    @Operation(summary = "Atualiza alguns campos de administração")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Administrador atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Administrador não encontrado")
    })
    @PutMapping("/atualizar/{id}")
    ResponseEntity<Administracao> atualizar(@PathVariable Long id, @RequestBody Map<String, Object> campos);

    @Operation(summary = "Deleta um administrador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Administrador deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Administrador não encontrado")
    })
    @DeleteMapping("/deletar/{id}")
    ResponseEntity<String> deletarUsuario(@PathVariable Long id);

    @Operation(summary = "Faz login de um administrador na área restrita")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @PostMapping("/loginArea")
    ResponseEntity<LoginAdmResponseDTO> loginAreaRestrita(@Valid @RequestBody LoginAdmRequestDTO loginAdmRequestDTO);
}
