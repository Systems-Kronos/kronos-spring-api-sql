package com.kronosapisql.repository;

import com.kronosapisql.model.Mensagem;
import com.kronosapisql.model.Setor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    public List<Mensagem> findByEmpresaId(long id);

}
