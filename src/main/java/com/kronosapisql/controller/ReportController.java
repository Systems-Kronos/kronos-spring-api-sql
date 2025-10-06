package com.kronosapisql.controller;

import com.kronosapisql.dto.ReportDTO;
import com.kronosapisql.dto.ReportFunctionDTO;
import com.kronosapisql.dto.StatusUpdateDTO;
import com.kronosapisql.model.Report;
import com.kronosapisql.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Reports", description = "Operações relacionadas aos reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @Operation(summary = "Lista todos os reports")
    @GetMapping("/listar")
    public ResponseEntity<List<Report>> listarReport() {
        List<Report> reports = reportService.listar();
        if (reports.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reports);
    }

    @Operation(summary = "Lista todos os reports dos usuários de um gestor específico baseado na function")
    @GetMapping("/selecionarFunction/{idGestor}")
    public List<ReportFunctionDTO> listarReportsFuncionariosGestor(@PathVariable Long idGestor) {
        return reportService.listarReportsFuncionariosGestor(idGestor);
    }

    @Operation(summary = "Lista report pelo ID")
    @GetMapping("/selecionar/{id}")
    public ResponseEntity<Report> buscarPorId(@PathVariable Long id) {
        Report report = reportService.buscarPorId(id);
        return ResponseEntity.ok(report);
    }

    @Operation(summary = "Lista report pelo status")
    @GetMapping("/selecionarStatus/{status}")
    public ResponseEntity<List<Report>> buscarPorStatus(@PathVariable String status) {
        List<Report> reports = reportService.buscarPorStatus(status);
        if (reports.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reports);
    }

    @Operation(summary = "Inserir um novo report")
    @PostMapping("/adicionar")
    public ResponseEntity<Report> inserirReport(@RequestBody ReportDTO dto) {
        Report reportSalvo = reportService.criarReport(dto);
        return ResponseEntity.ok(reportSalvo);
    }

    @Operation(summary = "Atualiza um report")
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizarReport(@Valid @RequestBody Report report) {
        reportService.atualizar(report);
        return ResponseEntity.ok("Report atualizada com sucesso.");
    }

    @Operation(summary = "Atualiza o status de um report")
    @PutMapping("/atualizarStatus/{id}")
    public ResponseEntity<String> atualizarStatus(@PathVariable Long id, @RequestBody StatusUpdateDTO dto) {
        reportService.atualizarStatus(id, dto.getStatus());
        return ResponseEntity.ok("Status do report atualizado com sucesso.");
    }

    @Operation(summary = "Deleta um Report pelo ID")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarReport(@PathVariable Long id) {
        reportService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
