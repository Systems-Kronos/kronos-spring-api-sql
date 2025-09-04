package com.kronosapisql.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String path = request.getRequestURI();

        if (path.equals("/api/usuario/login") || path.equals("/api/usuario/adicionar")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                String subject = jwtUtil.getSubject(token);

                if (jwtUtil.validarToken(token, subject)) {
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    subject,
                                    null,
                                    List.of(new SimpleGrantedAuthority("USER"))
                            );
                    SecurityContextHolder.getContext().setAuthentication(auth);

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
