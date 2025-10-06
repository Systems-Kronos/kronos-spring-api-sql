package com.kronosapisql.service;

import com.kronosapisql.dto.ReportDTO;
import com.kronosapisql.dto.ReportFunctionDTO;
import com.kronosapisql.enums.OpcaoStatus;
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

    public List<Report> buscarPorStatus(String status) {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status do report não pode ser nulo");
        }

        OpcaoStatus statusEnum = OpcaoStatus.fromValorBanco(status);
        String statusDb = statusEnum.getValorBanco();
        return reportRepository.findByStatusNative(statusDb);
    }

    public List<ReportFunctionDTO> listarReportsFuncionariosGestor(Long idGestor) {
        return reportRepository.listarReportsFuncionariosGestorRaw(idGestor)
                .stream()
                .map(ReportFunctionDTO::fromRow)
                .toList();
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

    public void atualizarStatus(Long id, String status) {
        if (id == null || status == null || status.isBlank()) {
            throw new IllegalArgumentException("Id e status não podem ser nulos!");
        }
        reportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Report não encontrada com ID: " + id));

        OpcaoStatus statusEnum = OpcaoStatus.fromValorBanco(status);
        String statusBanco = statusEnum.getValorBanco();
        reportRepository.atualizarStatusNative(id, statusBanco);
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

        OpcaoStatus statusEnum = OpcaoStatus.fromValorBanco(dto.getStatus());
        String statusDb = statusEnum.getValorBanco();
        reportRepository.inserirReportNative(dto.getDescricao(), dto.getProblema(), statusDb, tarefa.getId(), usuario.getId());

        Report report = new Report();
        report.setDescricao(dto.getDescricao());
        report.setProblema(dto.getProblema());
        report.setStatus(statusEnum);
        report.setUsuario(usuario);
        report.setTarefa(tarefa);
        return report;
    }
}
