package com.kronosapisql.repository;

import com.kronosapisql.model.Administracao;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministracaoRepository extends JpaRepository<Administracao, Long> {

    Optional<Administracao> findById(Long id);

    Optional<Administracao> findByEmail(String email);

}
