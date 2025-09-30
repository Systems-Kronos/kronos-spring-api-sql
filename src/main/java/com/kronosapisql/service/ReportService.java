package com.kronosapisql.service;

import com.kronosapisql.dto.ReportDTO;
import com.kronosapisql.model.OpcaoStatus;
import com.kronosapisql.model.Report;
import com.kronosapisql.model.Tarefa;
import com.kronosapisql.model.Usuario;
import com.kronosapisql.repository.ReportRepository;
import com.kronosapisql.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import com.kronosapisql.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;


    public ReportService(ReportRepository reportRepository, TarefaRepository tarefaRepository, UsuarioRepository usuarioRepository) {
        this.reportRepository = reportRepository;
        this.tarefaRepository = tarefaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Report buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID do report não pode ser nulo");
        }
        return reportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Report não encontrado com ID " + id));
    }

    public Report buscarPorStatus(String status) {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status do report não pode ser nulo");
        }
        return reportRepository.findByStatus(status)
                .orElseThrow(() -> new EntityNotFoundException("Report não encontrado com status: " + status));
    }

    public List<Report> listar() {
        return reportRepository.findAll();
    }

    public Report salvar(Report report) {
        if (report == null) {
            throw new IllegalArgumentException("Report não pode ser nulo");
        }
        return reportRepository.save(report);
    }

    public Report atualizar(Report report) {
        if (report == null) {
            throw new IllegalArgumentException("Report não pode ser nulo");
        }
        if (!reportRepository.existsById(report.getId())) {
            throw new EntityNotFoundException("Report não encontrado com ID " + report.getId());
        }
        return reportRepository.save(report);
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (!reportRepository.existsById(id)) {
            throw new EntityNotFoundException("Report não encontrado com ID " + id);
        }
        reportRepository.deleteById(id);
    }

    @Transactional
    public Report criarReport(ReportDTO dto) {
        Tarefa tarefa = tarefaRepository.findById(dto.getIdTarefa())
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID " + dto.getIdTarefa()));

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID " + dto.getIdUsuario()));

        String statusDb = converterStatusParaBanco(dto.getStatus());

        reportRepository.inserirReportNative(dto.getDescricao(),dto.getProblema(), statusDb, usuario.getId(), tarefa.getId());

        Report report = new Report();
        report.setDescricao(dto.getDescricao());
        report.setProblema(dto.getProblema());
        report.setStatus(OpcaoStatus.valueOf(statusDb.toUpperCase().replace(" ", "_")));
        report.setUsuario(usuario);
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
}
