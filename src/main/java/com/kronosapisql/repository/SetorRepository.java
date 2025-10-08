package com.kronosapisql.repository;

import com.kronosapisql.model.Setor;
import com.kronosapisql.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SetorRepository extends JpaRepository<Setor, Long> {
    Optional<Setor> findById(Long id);

    List<Setor> findByEmpresaId(long id);
}
