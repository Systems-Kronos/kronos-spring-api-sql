package com.kronosapisql.repository;

import com.kronosapisql.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, String> {
}

