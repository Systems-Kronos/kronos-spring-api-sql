package com.kronosapisql.repository;

import com.kronosapisql.model.LogAtribuicaoTarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LogAtribuicaoTarefaRepository extends JpaRepository<LogAtribuicaoTarefa, Long> {

    @Override
    Optional<LogAtribuicaoTarefa> findById(Long aLong);

}
