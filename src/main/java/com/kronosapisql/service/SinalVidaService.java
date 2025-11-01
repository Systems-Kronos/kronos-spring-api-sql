package com.kronosapisql.service;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

@Service
public class SinalVidaService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SinalVidaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void enviarSinalVida(Long idUsuario, String localUso) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("public")
                .withProcedureName("sp_envia_sinal_vida");

        jdbcCall.execute(Map.of(
                "p_nCdUsuario", idUsuario,
                "p_LocalUso", localUso
        ));
    }
}
