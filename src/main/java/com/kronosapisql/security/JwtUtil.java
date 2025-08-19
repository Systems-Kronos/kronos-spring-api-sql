package com.kronosapisql.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${JWT.SECRET}")
    private String secret;

    private String secretKey; // não estático

    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    @PostConstruct
    public void init() {
        this.secretKey = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String gerarToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validarToken(String token, String subject) {
        Claims claims = getClaims(token);
        String tokenSubject = claims.getSubject();
        Date expiration = claims.getExpiration();
        return tokenSubject.equals(subject) && expiration.after(new Date());
    }

    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }
}
