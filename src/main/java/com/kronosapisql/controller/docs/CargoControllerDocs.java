package com.kronosapisql.controller.docs;

import com.kronosapisql.model.Cargo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Cargo", description = "Operações relacionadas ao cargo")
@RequestMapping("/api/cargo")
public interface CargoControllerDocs {

    @Operation(summary = "Lista todos os cargos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de cargos retornada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @GetMapping("/listar")
    List<Cargo> listarCargo();

    @Operation(summary = "Lista um cargo pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cargo encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cargo não encontrado")
    })
    @GetMapping("/selecionarId/{id}")
    ResponseEntity<Cargo> selecionarPeloId(@PathVariable Long id);

    @Operation(summary = "Adiciona um novo cargo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cargo adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/adicionar")
    ResponseEntity<String> adicionarCargo(@RequestBody @Valid Cargo cargo);

    @Operation(summary = "Atualiza um cargo existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cargo atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping("/atualizar")
    ResponseEntity<String> atualizarCargo(@RequestBody @Valid Cargo cargo);

    @Operation(summary = "Deleta um cargo pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cargo removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cargo não encontrado")
    })
    @DeleteMapping("/deletar/{id}")
    ResponseEntity<String> deletarCargo(@PathVariable Long id);
}
