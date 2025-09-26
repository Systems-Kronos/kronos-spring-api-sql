package com.kronosapisql.service;

import com.kronosapisql.dto.ReportDTO;
import com.kronosapisql.model.OpcaoStatus;
import com.kronosapisql.model.Report;
import com.kronosapisql.model.Tarefa;
import com.kronosapisql.repository.ReportRepository;
import com.kronosapisql.repository.TarefaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final TarefaRepository tarefaRepository;

    public ReportService(ReportRepository reportRepository, TarefaRepository tarefaRepository) {
        this.reportRepository = reportRepository;
        this.tarefaRepository = tarefaRepository;
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

    @Transactional
    public Report criarReport(ReportDTO dto) {
        // Busca a tarefa pelo ID
        Tarefa tarefa = tarefaRepository.findById(dto.getIdTarefa())
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID " + dto.getIdTarefa()));

        // Converte status para o valor exato que o banco espera
        String statusDb = converterStatusParaBanco(dto.getStatus());

        // Chama a native query
        reportRepository.inserirReportNative(dto.getDescricao(), dto.getProblema(), statusDb, tarefa.getId());

        // Retorna um objeto Report “simulado” ou busca o último inserido se quiser retornar completo
        Report report = new Report();
        report.setDescricao(dto.getDescricao());
        report.setProblema(dto.getProblema());
        report.setStatus(OpcaoStatus.valueOf(statusDb.toUpperCase().replace(" ", "_"))); // opcional, para retornar enum
        report.setTarefa(tarefa);

        return report;
    }

    private String converterStatusParaBanco(String status) {
        switch (status.trim().toLowerCase()) {
            case "pendente": return "Pendente";
            case "em andamento": return "Em Andamento";
            case "concluído":
            case "concluída": return "Concluído";
            case "cancelado":
            case "cancelada": return "Cancelada";
            default: throw new IllegalArgumentException("Status inválido: " + status);
        }
    }



    public void atualizar(Report report) {
        this.reportRepository.save(report);
    }

    public void deletar(String id) {
        reportRepository.deleteById(id);
    }
}
