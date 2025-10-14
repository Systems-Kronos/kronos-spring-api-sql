package com.kronosapisql.repository;

import com.kronosapisql.model.HabilidadeUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HabilidadeUsuarioRepository extends JpaRepository<HabilidadeUsuario, Long> {
    List<HabilidadeUsuario> findByUsuarioId(Long id);
}
