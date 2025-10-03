package com.kronosapisql.repository;

import com.kronosapisql.model.Report;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByStatus(String status);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO report (cDescricao, cProblema, cStatus, nCdTarefa, nCdUsuario) " +
            "VALUES (:descricao, :problema, CAST(:status AS opcao_status), :tarefaId, :usuarioId)", nativeQuery = true)
    void inserirReportNative(@Param("descricao") String descricao,
                             @Param("problema") String problema,
                             @Param("status") String status,
                             @Param("tarefaId") Long tarefaId,
                             @Param("usuarioId") Long usuarioId);

    @Query(value = "SELECT * FROM fn_reports_gestor(:idGestor)", nativeQuery = true)
    List<Object[]> listarReportsFuncionariosGestorRaw(@Param("idGestor") Long idGestor);
}
