package com.kronosapisql.interceptor;

import com.kronosapisql.service.SinalVidaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SinalVidaInterceptor implements HandlerInterceptor {

    private final SinalVidaService sinalVidaService;

    public SinalVidaInterceptor(SinalVidaService sinalVidaService) {
        this.sinalVidaService = sinalVidaService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            String userAgent = request.getHeader("User-Agent");
            String localUso = identificarPlataforma(userAgent);

            String idStr = request.getHeader("X-User-Id");
            if (idStr != null) {
                Long idUsuario = Long.parseLong(idStr);
                sinalVidaService.enviarSinalVida(idUsuario, localUso);
                System.out.println("Deu certo!!");

            }

        } catch (Exception e) {
            System.err.println("Erro ao enviar sinal de vida: " + e.getMessage());
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
}
