package com.kronosapisql.service;

import com.kronosapisql.model.Empresa;
import com.kronosapisql.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa selecionarPeloId(long id) {
        return empresaRepository.findById(id).get();
    }

    public List<Empresa> selecionar() {
        return empresaRepository.findAll();
    }

    public void salvar(Empresa empresa) {
        empresaRepository.save(empresa);
    }

    public void deletar(long id) {
        empresaRepository.delete(empresaRepository.findById(id).get());
    }
    public void atualizar(Empresa empresa) {
        empresaRepository.save(empresa);
    }

}
