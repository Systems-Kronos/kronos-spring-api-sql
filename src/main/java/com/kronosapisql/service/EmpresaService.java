package com.kronosapisql.service;

import com.kronosapisql.model.Empresa;
import com.kronosapisql.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Optional<Empresa> selecionarPeloId(long id) {
        return this.empresaRepository.findById(id);
    }

    public List<Empresa> selecionar() {
        return empresaRepository.findAll();
    }

    public void salvar(Empresa empresa) {
        empresaRepository.save(empresa);
    }

    public void deletar(long id) {
        this.empresaRepository.deleteById(id);
    }

    public void atualizar(Empresa empresa) {
        empresaRepository.save(empresa);
    }
}
