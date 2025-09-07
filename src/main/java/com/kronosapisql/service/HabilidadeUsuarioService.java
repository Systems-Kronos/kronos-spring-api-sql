package com.kronosapisql.service;


import com.kronosapisql.model.HabilidadeUsuario;
import com.kronosapisql.repository.HabilidadeUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabilidadeUsuarioService {

    private final HabilidadeUsuarioRepository habilidadeUsuarioRepository;

    public HabilidadeUsuarioService(HabilidadeUsuarioRepository habilidadeUsuarioRepository) {
        this.habilidadeUsuarioRepository = habilidadeUsuarioRepository;
    }

    public List<HabilidadeUsuario> selecionar() {
        return habilidadeUsuarioRepository.findAll();
    }

    public Optional<HabilidadeUsuario> selecionarPeloId(Long id) {
        return habilidadeUsuarioRepository.findById(id);
    }

    public HabilidadeUsuario salvar(HabilidadeUsuario habilidadeUsuario) {
        return habilidadeUsuarioRepository.save(habilidadeUsuario);
    }

    public void deletar(Long id) {
        this.habilidadeUsuarioRepository.deleteById(id);
    }

    public void atualizar(HabilidadeUsuario habilidadeUsuario) {
        this.habilidadeUsuarioRepository.save(habilidadeUsuario);
    }

}

