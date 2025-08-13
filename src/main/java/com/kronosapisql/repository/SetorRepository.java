package com.kronosapisql.repository;

import com.kronosapisql.model.Setor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SetorRepository extends JpaRepository<Setor, Long> {
    public List<Setor> findByEmpresaId(long id);
}
