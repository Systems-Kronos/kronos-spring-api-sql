package com.kronosapisql.controller;

import com.kronosapisql.controller.docs.CargoControllerDocs;
import com.kronosapisql.model.Cargo;
import com.kronosapisql.service.CargoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CargoController implements CargoControllerDocs {

    private final CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @Override
    public List<Cargo> listarCargo() {
        return cargoService.listar();
    }

    @Override
    public ResponseEntity<Cargo> selecionarPeloId(Long id) {
        return ResponseEntity.ok(cargoService.buscarPorId(id));
    }

    @Override
    public ResponseEntity<String> adicionarCargo(@Valid Cargo cargo) {
        cargoService.salvar(cargo);
        return ResponseEntity.ok("Cargo adicionado com sucesso.");
    }

    @Override
    public ResponseEntity<String> atualizarCargo(@Valid Cargo cargo) {
        cargoService.atualizar(cargo);
        return ResponseEntity.ok("Cargo atualizado com sucesso.");
    }

    @Override
    public ResponseEntity<String> deletarCargo(Long id) {
        cargoService.deletar(id);
        return ResponseEntity.ok("Cargo deletado com sucesso.");
    }
}
