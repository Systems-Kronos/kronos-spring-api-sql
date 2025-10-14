package com.kronosapisql.service;

import com.kronosapisql.model.Habilidade;
import com.kronosapisql.model.HabilidadeUsuario;
import com.kronosapisql.model.Usuario;
import com.kronosapisql.repository.HabilidadeUsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabilidadeUsuarioService {
    private final HabilidadeUsuarioRepository habilidadeUsuarioRepository;

    public HabilidadeUsuarioService(HabilidadeUsuarioRepository habilidadeUsuarioRepository) {
        this.habilidadeUsuarioRepository = habilidadeUsuarioRepository;
    }

    public List<HabilidadeUsuario> buscarPorUsuario(Long idUsuario) {
        if (idUsuario == null) {
            throw new IllegalArgumentException("ID do usuário não pode ser nulo");
        }
        List<HabilidadeUsuario> habilidades = habilidadeUsuarioRepository.findByUsuarioId(idUsuario);
        if (habilidades.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma habilidade encontrada para o usuário com ID: " + idUsuario);
        }
        return habilidades;
    }

    public List<HabilidadeUsuario> listar() {
        return habilidadeUsuarioRepository.findAll();
    }

    @Transactional
    public List<HabilidadeUsuario> inserir(Long idUsuario, List<Long> idsHabilidade) {
        if (idUsuario == null) {
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo");
        }

        if (idsHabilidade == null || idsHabilidade.isEmpty()) {
            throw new IllegalArgumentException("A lista de IDs de habilidade não pode ser nula nem vazia");
        }

        // Cria os objetos intermediários ligando usuário e habilidades
        List<HabilidadeUsuario> habilidadesUsuario = idsHabilidade.stream()
                .map(idHab -> HabilidadeUsuario.builder()
                        .usuario(Usuario.builder().id(idUsuario).build())
                        .habilidade(Habilidade.builder().id(idHab).build())
                        .build())
                .toList();

        // Salva todos de uma vez
        return habilidadeUsuarioRepository.saveAll(habilidadesUsuario);
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
