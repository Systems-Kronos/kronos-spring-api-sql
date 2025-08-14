package com.kronosapisql.service;

import com.kronosapisql.model.Mensagem;
import com.kronosapisql.repository.MensagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensagemService {
    private final MensagemRepository mensagemRepository;

    public MensagemService(MensagemRepository mensagemRepository) {
        this.mensagemRepository = mensagemRepository;
    }

    public Optional<Mensagem> selecionarPeloId(Long id) {
        return this.mensagemRepository.findById(id);
    }

    public Mensagem salvar(Mensagem mensagem) {
        return this.mensagemRepository.save(mensagem);
    }

    public void deletar(Long id) {
        this.mensagemRepository.deleteById(id);
    }

    public void atualizar(Mensagem mensagem) {
        this.mensagemRepository.save(mensagem);
    }
}

