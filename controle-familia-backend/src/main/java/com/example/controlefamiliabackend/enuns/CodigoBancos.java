package com.example.controlefamiliabackend.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CodigoBancos {

    BANCO_DO_BRASIL("Banco do Brasil", "001"),

    SICOOB("Sicoob", "756"),

    SICREDI("Sicredi", "748"),

    NUBANK("Nubank", "260"),

    AMBAR_BANK("Ambar Bank", "000");

    private final String descricaoBanco;

    private final String codigoBanco;

}
