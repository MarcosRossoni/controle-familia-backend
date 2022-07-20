package com.example.controlefamiliabackend.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoContaBanco {

    POUPANCA("P"),

    CONTA_CORRENTE("CC");

    private final String descricao;

}
