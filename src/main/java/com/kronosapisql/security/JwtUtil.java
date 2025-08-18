package com.kronosapisql.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import java.util.Base64;
import java.util.Date;

public class JwtUtil {
    @Value("${JWT_SECRET}")
    private String secret;
    private static String SECRET_KEY;
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    @PostConstruct
    public void init() {
        SECRET_KEY = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public static String gerarToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    private static Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public static boolean validarToken(String token, String subject) {
        Claims claims = getClaims(token);
        String tokenSubject = claims.getSubject();
        Date expiration = claims.getExpiration();
        return (tokenSubject.equals(subject) && expiration.after(new Date()));
    }

    public static String getSubject(String token) {
        return getClaims(token).getSubject();
    }
}
