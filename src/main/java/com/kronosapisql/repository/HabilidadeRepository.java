package com.kronosapisql.repository;

import com.kronosapisql.model.Habilidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabilidadeRepository extends JpaRepository<Habilidade, Long> {
    List<Habilidade> findByEmpresaId(long id);
}
