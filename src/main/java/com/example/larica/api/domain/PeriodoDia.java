package com.example.larica.api.domain;

import java.time.LocalTime;

public enum PeriodoDia {
    MANHA(LocalTime.of(6, 0), LocalTime.of(11, 0)),
    ALMOCO(LocalTime.of(11, 0), LocalTime.of(14, 0)),
    TARDE(LocalTime.of(14, 0), LocalTime.of(18, 0)),
    NOITE(LocalTime.of(18, 0), LocalTime.of(23, 59, 59)),
    MADRUGADA(LocalTime.MIN, LocalTime.of(6, 0));

    private final LocalTime inicio;
    private final LocalTime fim;

    PeriodoDia(LocalTime inicio, LocalTime fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public static PeriodoDia agora() {
        LocalTime agora = LocalTime.now();
        for (PeriodoDia periodo : values()) {
            // Caso especial para MADRUGADA que cruza a meia-noite
            if (periodo == MADRUGADA) {
                if (agora.isBefore(periodo.fim) && !agora.isBefore(periodo.inicio)) {
                    return periodo;
                }
            } else if (!agora.isBefore(periodo.inicio) && agora.isBefore(periodo.fim)) {
                return periodo;
            }
        }
        // Fallback para o segundo exato da meia-noite
        return MADRUGADA;
    }
}
