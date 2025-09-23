package com.kronosapisql.service;

import com.kronosapisql.model.Report;
import com.kronosapisql.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Report> listarTodosReports() {
        return reportRepository.findAll();
    }

    public Optional<Report> buscarPorId(String id) {
        return reportRepository.findById(id);
    }

    public Optional<Report> buscarPorStatus(String status) {
        return reportRepository.findByStatus(status);
    }

    public Report salvar(Report report) {
        return reportRepository.save(report);
    }

    public void atualizar(Report report) {
        this.reportRepository.save(report);
    }

    public void deletar(String id) {
        reportRepository.deleteById(id);
    }
}
