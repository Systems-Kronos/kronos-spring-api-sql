package com.kronosapisql.enums;

import lombok.Getter;

@Getter
public enum OpcaoStatus {
    PENDENTE("Pendente", 1),
    EM_ANDAMENTO("Em Andamento", 2),
    CONCLUIDA("Concluída", 3),
    CONCLUIDO("Concluído", 4),
    CANCELADA("Cancelada", 5),
    CANCELADO("Cancelado", 6);

    private final String valorBanco;
    private final int codigo;

    OpcaoStatus(String valorBanco, Integer codigo) {
        this.valorBanco = valorBanco;
        this.codigo = codigo;
    }

    public static OpcaoStatus fromValorBanco(String valorBanco) {
        for (OpcaoStatus status : OpcaoStatus.values()) {
            if (status.valorBanco.equalsIgnoreCase(valorBanco)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Valor de OpcaoStatus inválido: " + valorBanco);
    }

    public static OpcaoStatus fromCodigo(int codigo) {
        for (OpcaoStatus status : OpcaoStatus.values()) {
            if (status.codigo == codigo) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de OpcaoStatus inválido: " + codigo);
    }
}
