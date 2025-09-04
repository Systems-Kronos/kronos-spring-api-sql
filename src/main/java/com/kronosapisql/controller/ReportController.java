package com.kronosapisql.controller;

import com.kronosapisql.model.Report;
import com.kronosapisql.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report")
@Tag(name = "Reports", description = "Operações relacionadas aos reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @Operation(summary = "Lista todos os reports")
    @GetMapping("/listar")
    public ResponseEntity<List<Report>> listarTodosReports() {
        List<Report> reports = reportService.listarTodosReports();
        if (reports.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reports);
    }

    @Operation(summary = "Busca report pelo ID")
    @GetMapping("/listar/{id}")
    public ResponseEntity<Report> buscarPorId(@PathVariable String id) {
        return reportService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Inserir um novo report")
    @PostMapping("/adicionar")
    public ResponseEntity<Report> inserirReport(@RequestBody Report report) {
        Report salvo = reportService.salvar(report);

        return ResponseEntity.status(201).body(salvo);
    }

    @Operation(summary = "Atualiza um report")
    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizar(@Valid @RequestBody Report report) {
        reportService.atualizar(report);
        return ResponseEntity.ok("Report atualizada com sucesso.");
    }

    @Operation(summary = "Deleta um Report pelo ID")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarReport(@PathVariable String id) {
        reportService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}




