package com.kronosapisql.service;

import com.kronosapisql.model.Usuario;
import com.kronosapisql.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    public Optional<Usuario> selecionarPeloId(Long id) {
        return this.usuarioRepository.findById(id);
    }

    public List<Usuario> selecionar() {
        return this.usuarioRepository.findAll();
    }

    public Usuario salvar(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        this.usuarioRepository.deleteById(id);
    }

    public void atualizar(Usuario usuario) {
        this.usuarioRepository.save(usuario);
    }
}
