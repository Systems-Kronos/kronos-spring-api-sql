package com.kronosapisql.controller;

import com.kronosapisql.model.Cargo;
import com.kronosapisql.service.CargoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cargo")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Cargo", description = "Operações relacionadas ao cargo")
public class CargoController {
    private final CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @Operation(summary = "Lista todos os cargos")
    @GetMapping("/selecionar")
    public List<Cargo> selecionar() {
        return cargoService.selecionar();
    }

    @Operation(summary = "Lista um cargo pelo id")
    @GetMapping("/selecionarId/{id}")
    public Optional<Cargo> selecionarPeloId(@PathVariable Long id) {
        return cargoService.selecionarPeloId(id);
    }

    @Operation(summary = "Adiciona um novo cargo")
    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionar(@Valid @RequestBody Cargo cargo) {
        cargoService.salvar(cargo);
        return ResponseEntity.ok("Cargo adicionado com sucesso.");
    }

    @Operation(summary = "Atualiza um cargo")
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@Valid @RequestBody Cargo cargo) {
        cargoService.atualizar(cargo);
        return ResponseEntity.ok("Cargo atualizado com sucesso.");
    }

    @Operation(summary = "Deleta um cargo")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        cargoService.deletar(id);
        return ResponseEntity.ok("Cargo deletado com sucesso.");
    }
}
