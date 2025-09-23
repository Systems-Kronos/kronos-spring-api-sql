package com.kronosapisql.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key chaveSecreta = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String gerarToken(String subject) {

        Date agora = new Date();
        Date meiaNoite = getProximaMeiaNoite();

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(agora)
                .setExpiration(meiaNoite)
                .signWith(chaveSecreta)
                .compact();
    }

    // Calcula a próxima meia-noite
    private Date getProximaMeiaNoite() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.DAY_OF_MONTH, 1); // amanhã
        cal.set(java.util.Calendar.HOUR_OF_DAY, 0);
        cal.set(java.util.Calendar.MINUTE, 0);
        cal.set(java.util.Calendar.SECOND, 0);
        cal.set(java.util.Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }

    public boolean validarToken(String token, String subject) {
        String subjectExtraido = getSubject(token);
        return subjectExtraido.equals(subject) && !isTokenExpirado(token);
    }

    private boolean isTokenExpirado(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(chaveSecreta)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
