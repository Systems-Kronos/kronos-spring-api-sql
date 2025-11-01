package com.kronosapisql.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class SinalVidaService {

    private final JdbcTemplate jdbcTemplate;

    public SinalVidaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void enviarSinalVida(Long idUsuario, String localUso) {
        if (idUsuario == null || localUso == null) return;

        String sql = "CALL public.sp_envia_sinal_vida(?, ?, ?)";
        jdbcTemplate.update(sql,
                idUsuario,
                localUso,
                Timestamp.valueOf(LocalDateTime.now())
        );
    }
}
