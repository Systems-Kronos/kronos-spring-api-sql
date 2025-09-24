package com.kronosapisql.repository;

import com.kronosapisql.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, String> {
    Optional<Report> findByStatus(String status);
}
