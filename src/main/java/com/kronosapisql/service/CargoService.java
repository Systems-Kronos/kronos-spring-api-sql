package com.kronosapisql.service;

import com.kronosapisql.model.Cargo;
import com.kronosapisql.repository.CargoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {
    private final CargoRepository cargoRepository;

    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public Cargo buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return cargoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cargo não encontrado com ID: " + id));
    }

    public List<Cargo> listar() {
        return cargoRepository.findAll();
    }

    public Cargo salvar(Cargo cargo) {
        if (cargo == null) {
            throw new IllegalArgumentException("Cargo não pode ser nulo");
        }
        return cargoRepository.save(cargo);
    }

    public Cargo atualizar(Cargo cargo) {
        if (cargo == null) {
            throw new IllegalArgumentException("Cargo não pode ser nulo");
        }
        if (!cargoRepository.existsById(cargo.getId())) {
            throw new EntityNotFoundException("Cargo não encontrado com ID: " + cargo.getId());
        }
        return cargoRepository.save(cargo);
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (!cargoRepository.existsById(id)) {
            throw new EntityNotFoundException("Cargo não encontrado com ID: " + id);
        }
        cargoRepository.deleteById(id);
    }
}
