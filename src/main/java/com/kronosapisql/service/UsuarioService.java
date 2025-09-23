package com.kronosapisql.service;

import com.kronosapisql.model.Usuario;
import com.kronosapisql.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
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
        if (id == null) {
            throw new IllegalArgumentException("ID do usuário não pode ser nulo");
        }
        try {
            Optional<Usuario> usuario = this.usuarioRepository.findById(id);
            if (!usuario.isEmpty()) {
                throw new EntityNotFoundException("Usuário não encontrado com ID: " + id);
            }
            return usuario;
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuário por ID: " + e.getMessage());
        }
    }

    public Optional<Usuario> selecionarPeloCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
        }
        try {
            Optional<Usuario> usuario = this.usuarioRepository.findByCpf(cpf);
            if (!usuario.isPresent()) {
                throw new EntityNotFoundException("Usuário não encontrado com CPF: " + cpf);
            }
            return usuario;
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuário por CPF: " + e.getMessage());
        }
    }

    public List<Usuario> selecionar() {
        try {
            return this.usuarioRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar usuários: " + e.getMessage());
        }
    }

    public Usuario salvar(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        try {
            Optional<Usuario> existingUser = usuarioRepository.findByCpf(usuario.getCpf());
            if (existingUser.isPresent()) {
                throw new IllegalArgumentException("Já existe um usuário cadastrado com este CPF");
            }
            return this.usuarioRepository.save(usuario);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do usuário não pode ser nulo");
        }
        try {
            if (!usuarioRepository.existsById(id)) {
                throw new EntityNotFoundException("Usuário não encontrado com ID: " + id);
            }
            this.usuarioRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Usuário não encontrado com ID: " + id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar usuário: " + e.getMessage());
        }
    }

    public void atualizar(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        try {
            if (!usuarioRepository.existsById(usuario.getId())) {
                throw new EntityNotFoundException("Usuário não encontrado com ID: " + usuario.getId());
            }
            
            Optional<Usuario> existingUser = usuarioRepository.findByCpf(usuario.getCpf());
            if (existingUser.isPresent() && !existingUser.get().getId().equals(usuario.getId())) {
                throw new IllegalArgumentException("CPF já está sendo usado por outro usuário");
            }
            
            this.usuarioRepository.save(usuario);
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public Optional<Usuario> loginApp(String cpf, String senha) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
        }
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia");
        }
        
        try {
            Optional<Usuario> usuario = usuarioRepository.findByCpf(cpf);
            if (!usuario.isPresent()) {
                throw new BadCredentialsException("Credenciais inválidas");
            }

            Usuario verificarUsuario = usuario.get();
            if (!verificarUsuario.getSenha().equals(senha)) {
                throw new BadCredentialsException("Senha inválida");
            }
            if (!verificarUsuario.getAtivo()) {
                throw new BadCredentialsException("Usuário desligado");
            }
            
            return usuario;
        } catch (BadCredentialsException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao realizar login: " + e.getMessage());
        }
    }
}
