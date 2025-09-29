package com.kronosapisql.service;

import com.kronosapisql.model.Cargo;
import com.kronosapisql.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoService {
    private final CargoRepository cargoRepository;

    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public List<Cargo> selecionar() {
        try {
            return this.cargoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar cargos: " + e.getMessage());
        }
    }

    public Optional<Cargo> selecionarPeloId(Long id) {
        return cargoRepository.findById(id);
    }

    public Cargo salvar(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    public void atualizar(Cargo cargo) {
        this.cargoRepository.save(cargo);
    }

    public void deletar(Long id) {
        cargoRepository.deleteById(id);
    }
}
