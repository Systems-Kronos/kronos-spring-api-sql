package com.kronosapisql.util;

import com.kronosapisql.model.OpcaoStatus;

public class StatusMapper {
    public static OpcaoStatus toEnum(String status) {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status não pode ser nulo ou vazio!");
        }

        String normalizado = status.trim().toLowerCase();
        switch (normalizado) {
            case "pendente":
                return OpcaoStatus.PENDENTE;
            case "em andamento":
                return OpcaoStatus.EM_ANDAMENTO;
            case "concluido":
            case "concluído":
                return OpcaoStatus.CONCLUIDO;
            case "concluida":
            case "concluída":
                return OpcaoStatus.CONCLUIDA;
            case "cancelado":
                return OpcaoStatus.CANCELADO;
            case "cancelada":
                return OpcaoStatus.CANCELADA;
            default:
                throw new IllegalArgumentException("Status inválido: " + status);
        }
    }

    public static String toDatabaseValue(OpcaoStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status não pode ser nulo!");
        }

        switch (status) {
            case PENDENTE:
                return "Pendente";
            case EM_ANDAMENTO:
                return "Em Andamento";
            case CONCLUIDO:
                return "Concluído";
            case CONCLUIDA:
                return "Concluída";
            case CANCELADO:
                return "Cancelado";
            case CANCELADA:
                return "Cancelada";
            default:
                throw new IllegalArgumentException("Status inválido: " + status);
        }
    }
}
