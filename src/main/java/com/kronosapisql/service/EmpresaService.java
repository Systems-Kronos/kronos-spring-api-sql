package com.kronosapisql.service;

import com.kronosapisql.model.Empresa;
import com.kronosapisql.repository.EmpresaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrado com ID: " + id));
    }

    public List<Empresa> listar() {
        return empresaRepository.findAll();
    }

    public Empresa salvar(Empresa empresa) {
        if (empresa == null) {
            throw new IllegalArgumentException("Empresa não pode ser nula");
        }
        return empresaRepository.save(empresa);
    }

    public Empresa atualizar(Empresa empresa) {
        if (empresa == null) {
            throw new IllegalArgumentException("Empresa não pode ser nula");
        }
        if (!empresaRepository.existsById(empresa.getId())) {
            throw new EntityNotFoundException("Empresa não encontrado com ID: " + empresa.getId());
        }
        return empresaRepository.save(empresa);
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (!empresaRepository.existsById(id)) {
            throw new EntityNotFoundException("Empresa não encontrado com ID: " + id);
        }
        empresaRepository.deleteById(id);
    }
}
