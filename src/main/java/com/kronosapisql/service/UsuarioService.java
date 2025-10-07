package com.kronosapisql.service;

import com.kronosapisql.model.Setor;
import com.kronosapisql.model.Usuario;
import com.kronosapisql.repository.SetorRepository;
import com.kronosapisql.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final SetorRepository setorRepository;
    
    public UsuarioService(UsuarioRepository usuarioRepository, SetorRepository setorRepository){
        this.usuarioRepository = usuarioRepository;
        this.setorRepository = setorRepository;
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

    public Usuario atualizarParcial(Long id, Map<String, Object> campos) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Permitir apenas atualização dos campos específicos
        if (campos.containsKey("nome")) {
            usuario.setNome((String) campos.get("nome"));
        }

        if (campos.containsKey("email")) {
            usuario.setEmail((String) campos.get("email"));
        }

        if (campos.containsKey("booleanGestor")) {
            usuario.setBooleanGestor((Boolean) campos.get("booleanGestor"));
        }

        if (campos.containsKey("ativo")) {
            usuario.setAtivo((Boolean) campos.get("ativo"));
        }

        if (campos.containsKey("setor")) {
            Object setorObj = campos.get("setor");
            if (setorObj instanceof Map<?, ?> setorMap && setorMap.containsKey("id")) {
                Long setorId = ((Number) setorMap.get("id")).longValue();
                Setor setor = setorRepository.findById(setorId)
                        .orElseThrow(() -> new RuntimeException("Setor não encontrado"));
                usuario.setSetor(setor);
            }
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
