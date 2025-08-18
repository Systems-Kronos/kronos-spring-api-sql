package com.kronosapisql.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                String subject = JwtUtil.getSubject(token);

                if (JwtUtil.validarToken(token, subject)) {
                    System.out.println("Token válido para: " + subject);
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token inválido ou expirado");
                    return;
                }
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token inválido");
                return;
            }
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token não informado");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
