package com.kronosapisql.service;

import com.kronosapisql.model.Usuario;
import com.kronosapisql.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do usuário não pode ser nulo");
        }
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID " + id));
    }

    public Usuario buscarPorCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF do usuário não pode ser nulo");
        }
        return usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com CPF " + cpf));
    }

    public List<Usuario> listar() {
        return this.usuarioRepository.findAll();
    }

    public Usuario salvar(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        usuarioRepository.findByCpf(usuario.getCpf()).ifPresent(u -> {
            throw new IllegalArgumentException("Já existe um usuário com este CPF");
        });
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        if (!usuarioRepository.existsById(usuario.getId())) {
            throw new EntityNotFoundException("Usuário não encontrado com ID " + usuario.getId());
        }
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado com ID " + id);
        }
        usuarioRepository.deleteById(id);
    }

    public Usuario loginApp(String cpf, String senha) {
        Usuario usuario = buscarPorCpf(cpf);
        if (!usuario.getSenha().equals(senha)) {
            throw new BadCredentialsException("Senha inválida");
        }
        if (!usuario.getAtivo()) {
            throw new BadCredentialsException("Usuário inativo");
        }
        return usuario;
    }

    public Usuario loginPlataforma(String cpf, String senha) {
        Usuario usuario = loginApp(cpf, senha);
        if (!usuario.getBooleanGestor()) {
            throw new BadCredentialsException("Acesso restrito a gestores");
        }
        return usuario;
    }
}
