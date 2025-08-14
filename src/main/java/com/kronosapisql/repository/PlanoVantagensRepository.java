package com.kronosapisql.repository;

import com.kronosapisql.model.PlanoVantagens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanoVantagensRepository extends JpaRepository<PlanoVantagens, Long> {
    public List<PlanoVantagens> findByPlanoPagamento(Long id);
}
