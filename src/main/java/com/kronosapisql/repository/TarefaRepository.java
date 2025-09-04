package com.kronosapisql.repository;

import com.kronosapisql.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, String> {
}
