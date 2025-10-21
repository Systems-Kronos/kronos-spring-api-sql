package com.kronosapisql.service;

import com.kronosapisql.model.Administracao;
import com.kronosapisql.repository.AdministracaoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class AdministracaoService {

    private final AdministracaoRepository administracaoRepository;
    private final PasswordEncoder passwordEncoder;

    public AdministracaoService(AdministracaoRepository administracaoRepository, PasswordEncoder passwordEncoder){
        this.administracaoRepository = administracaoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Administracao buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do usuário não pode ser nulo");
        }
        return administracaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID " + id));
    }

    public Administracao buscarPorEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email do usuário não pode ser nulo");
        }
        return administracaoRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com esse email: " + email));
    }

    public List<Administracao> listar() {
        return this.administracaoRepository.findAll();
    }

    public Administracao salvar(Administracao administracao) {
        if (administracao == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        administracaoRepository.findByEmail(administracao.getEmail()).ifPresent(u -> {
            throw new IllegalArgumentException("Já existe um usuário com este email");
        });
        return administracaoRepository.save(administracao);
    }

    public Long criarAdministracao(Administracao administracao) {
        String senhaCriptografada = passwordEncoder.encode(administracao.getSenha());
        Administracao administracao1 = Administracao.builder()
                .nome(administracao.getNome())
                .email(administracao.getEmail())
                .senha(senhaCriptografada)
                .build();

        Administracao administracaoSalvo = administracaoRepository.save(administracao1);
        return administracaoSalvo.getId();
    }

    public Administracao atualizar(Long id, Map<String, Object> campos) {
        Administracao administracao = administracaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administaracao não encontrado"));

        if (campos.containsKey("nome")) {
            administracao.setNome((String) campos.get("nome"));
        }

        if (campos.containsKey("email")) {
            administracao.setEmail((String) campos.get("email"));
        }
        if (campos.containsKey("senha")) {
            String senhaCriptografada = passwordEncoder.encode((String) campos.get("senha"));
            administracao.setSenha(senhaCriptografada);
        }
        return administracaoRepository.save(administracao);
    }
     public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (!administracaoRepository.existsById(id)) {
            throw new EntityNotFoundException("Administração não encontrada com ID " + id);
        }
        administracaoRepository.deleteById(id);
    }

    public Administracao loginAreaRestrita(String email, String senha) {
        Administracao administracao = buscarPorEmail(email);
        if (!passwordEncoder.matches(senha, administracao.getSenha())) {
            throw new BadCredentialsException("Senha inválida");
        }
        return administracao;
    }

}

