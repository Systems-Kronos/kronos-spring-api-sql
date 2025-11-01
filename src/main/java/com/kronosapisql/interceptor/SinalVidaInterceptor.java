package com.kronosapisql.interceptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kronosapisql.model.Usuario;
import com.kronosapisql.repository.UsuarioRepository;
import com.kronosapisql.service.SinalVidaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@Component
public class SinalVidaInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(SinalVidaInterceptor.class);

    private final SinalVidaService sinalVidaService;
    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public SinalVidaInterceptor(SinalVidaService sinalVidaService, UsuarioRepository usuarioRepository) {
        this.sinalVidaService = sinalVidaService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            String userAgent = request.getHeader("User-Agent");
            String localUso = identificarPlataforma(userAgent);

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Long idUsuario = null;

            if (auth != null && auth.isAuthenticated() && !"anonymousUser".equalsIgnoreCase(auth.getName())) {
                String principalName = auth.getName();
                try {
                    idUsuario = Long.valueOf(principalName);
                    log.debug("ID obtido de auth.getName(): {}", idUsuario);
                } catch (NumberFormatException nfe) {
                    log.debug("auth.getName() não é numérico: {}", principalName);

                    String authHeader = request.getHeader("Authorization");
                    if (authHeader != null && authHeader.startsWith("Bearer ")) {
                        String token = authHeader.substring(7);
                        Long idFromToken = tryExtractIdFromJwtPayload(token);
                        if (idFromToken != null) {
                            idUsuario = idFromToken;
                            log.debug("ID extraído do JWT payload: {}", idUsuario);
                        }
                    }

                    if (idUsuario == null) {
                        try {
                            Optional<Usuario> usuOpt = usuarioRepository.findByEmail(principalName);
                            if (usuOpt.isPresent()) {
                                idUsuario = usuOpt.get().getId();
                                log.debug("ID obtido via usuarioRepository.findByEmail(): {}", idUsuario);
                            } else {
                                log.debug("Usuário não encontrado por email com principalName: {}", principalName);
                            }
                        } catch (Exception ex) {
                            log.debug("Erro ao buscar usuário por email: {}", ex.getMessage());
                        }
                    }
                }
            } else {
                log.debug("Authentication nulo/anonimo ou não autenticado.");
            }

            if (idUsuario != null) {
                sinalVidaService.enviarSinalVida(idUsuario, localUso);
                log.info("Sinal de vida enviado para id={} local={}", idUsuario, localUso);
            } else {
                log.debug("Não foi possível determinar id do usuário para enviar sinal de vida.");
            }

        } catch (Exception e) {
            log.error("Erro ao enviar sinal de vida", e);
        }

        return true;
    }

    private String identificarPlataforma(String userAgent) {
        if (userAgent == null) return "APP_WEB";
        String ua = userAgent.toLowerCase();
        if (ua.contains("mobile") || ua.contains("android") || ua.contains("iphone")) {
            return "APP_MOBILE";
        }
        return "APP_WEB";
    }

    private Long tryExtractIdFromJwtPayload(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length < 2) return null;
            String payloadB64 = parts[1];
            // ajustar Base64 url-safe
            Base64.Decoder decoder = Base64.getUrlDecoder();
            byte[] decoded = decoder.decode(payloadB64);
            String payloadJson = new String(decoded, StandardCharsets.UTF_8);
            JsonNode node = objectMapper.readTree(payloadJson);

            if (node.has("id")) {
                return node.get("id").asLong();
            }
            if (node.has("userId")) {
                return node.get("userId").asLong();
            }
            if (node.has("sub")) {
                String sub = node.get("sub").asText();
                try {
                    return Long.valueOf(sub);
                } catch (NumberFormatException ignored) { }
            }
        } catch (Exception e) {
            log.debug("Falha ao extrair id do JWT payload: {}", e.getMessage());
        }
        return null;
    }
}
