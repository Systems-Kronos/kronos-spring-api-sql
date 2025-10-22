package com.kronosapisql.controller;

import com.kronosapisql.controller.docs.ReportControllerDocs;
import com.kronosapisql.dto.ReportDTO;
import com.kronosapisql.dto.ReportFunctionDTO;
import com.kronosapisql.dto.StatusUpdateDTO;
import com.kronosapisql.model.Report;
import com.kronosapisql.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportController implements ReportControllerDocs {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public ResponseEntity<List<Report>> listarReport() {
        List<Report> reports = reportService.listar();
        if (reports.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reports);
    }

    @Override
    public List<ReportFunctionDTO> listarReportsFuncionariosGestor(Long idGestor) {
        return reportService.listarReportsFuncionariosGestor(idGestor);
    }

    @Override
    public ResponseEntity<Report> buscarPorId(Long id) {
        return ResponseEntity.ok(reportService.buscarPorId(id));
    }

    @Override
    public ResponseEntity<List<Report>> buscarPorStatus(String status) {
        List<Report> reports = reportService.buscarPorStatus(status);
        if (reports.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reports);
    }

    @Override
    public ResponseEntity<Report> inserirReport(ReportDTO dto) {
        return ResponseEntity.ok(reportService.criarReport(dto));
    }

    @Override
    public ResponseEntity<String> atualizarReport(Report report) {
        reportService.atualizar(report);
        return ResponseEntity.ok("Report atualizada com sucesso.");
    }

    @Override
    public ResponseEntity<String> atualizarStatus(Long id, StatusUpdateDTO dto) {
        reportService.atualizarStatus(id, dto.getStatus());
        return ResponseEntity.ok("Status do report atualizado com sucesso.");
    }

    @Override
    public ResponseEntity<Void> deletarReport(Long id) {
        reportService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
