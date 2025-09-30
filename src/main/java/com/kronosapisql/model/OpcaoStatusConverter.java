package com.kronosapisql.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OpcaoStatusConverter implements AttributeConverter<OpcaoStatus, String> {
    @Override
    public String convertToDatabaseColumn(OpcaoStatus status) {
        if (status == null) return null;
        switch (status) {
            case PENDENTE: return "Pendente";
            case EM_ANDAMENTO: return "Em Andamento";
            case CONCLUIDA: case CONCLUIDO: return "Concluída";
            case CANCELADA: case CANCELADO: return "Cancelada";
            default: throw new IllegalArgumentException("Status inválido: " + status);
        }
    }

    @Override
    public OpcaoStatus convertToEntityAttribute(String value) {
        if (value == null) return null;
        switch (value) {
            case "Pendente": return OpcaoStatus.PENDENTE;
            case "Em Andamento": return OpcaoStatus.EM_ANDAMENTO;
            case "Concluída": return OpcaoStatus.CONCLUIDA;
            case "Cancelada": return OpcaoStatus.CANCELADA;
            default: throw new IllegalArgumentException("Valor inválido no banco: " + value);
        }
    }
}
