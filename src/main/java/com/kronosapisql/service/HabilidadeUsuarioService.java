package com.kronosapisql.service;

import com.kronosapisql.model.HabilidadeUsuario;
import com.kronosapisql.repository.HabilidadeUsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabilidadeUsuarioService {
    private final HabilidadeUsuarioRepository habilidadeUsuarioRepository;

    public HabilidadeUsuarioService(HabilidadeUsuarioRepository habilidadeUsuarioRepository) {
        this.habilidadeUsuarioRepository = habilidadeUsuarioRepository;
    }

    public HabilidadeUsuario buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return habilidadeUsuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Habilidade do usuário não encontrado com ID: " + id));
    }

    public List<HabilidadeUsuario> listar() {
        return habilidadeUsuarioRepository.findAll();
    }

    public HabilidadeUsuario salvar(HabilidadeUsuario habilidade) {
        if (habilidade == null) {
            throw new IllegalArgumentException("Habilidade não pode ser nula");
        }
        return habilidadeUsuarioRepository.save(habilidade);
    }

    public HabilidadeUsuario atualizar(HabilidadeUsuario habilidade) {
        if (habilidade == null) {
            throw new IllegalArgumentException("Habilidade não pode ser nula");
        }
        if (!habilidadeUsuarioRepository.existsById(habilidade.getId())) {
            throw new EntityNotFoundException("Habilidade não encontrado com ID: " + habilidade.getId());
        }
        return habilidadeUsuarioRepository.save(habilidade);
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (!habilidadeUsuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Habilidade não encontrado com ID: " + id);
        }
        habilidadeUsuarioRepository.deleteById(id);
    }
}
