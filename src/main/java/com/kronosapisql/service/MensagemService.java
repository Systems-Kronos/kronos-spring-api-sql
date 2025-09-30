package com.kronosapisql.service;

import com.kronosapisql.model.Mensagem;
import com.kronosapisql.repository.MensagemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MensagemService {
    private final MensagemRepository mensagemRepository;

    public MensagemService(MensagemRepository mensagemRepository) {
        this.mensagemRepository = mensagemRepository;
    }

    public Mensagem buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return mensagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mensagem não encontrada com ID: " + id));
    }

    public Mensagem salvar(Mensagem mensagem) {
        if (mensagem == null) {
            throw new IllegalArgumentException("Mensagem não pode ser nula");
        }
        return this.mensagemRepository.save(mensagem);
    }

    public Mensagem atualizar(Mensagem mensagem) {
        if (mensagem == null) {
            throw new IllegalArgumentException("Mensagem não pode ser nula");
        }
        if (!mensagemRepository.existsById(mensagem.getId())) {
            throw new EntityNotFoundException("Mensagem não encontrada com ID: " + mensagem.getId());
        }
        return mensagemRepository.save(mensagem);
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (!mensagemRepository.existsById(id)) {
            throw new EntityNotFoundException("Mensagem não encontrada com ID: " + id);
        }
        mensagemRepository.deleteById(id);
    }
}
