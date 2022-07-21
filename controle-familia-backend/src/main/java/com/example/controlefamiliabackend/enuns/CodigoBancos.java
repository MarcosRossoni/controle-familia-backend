package com.example.controlefamiliabackend.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

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

    public static CodigoBancos shortName(String codigoBanco){
        for(CodigoBancos value : CodigoBancos.values()){
            if(Objects.equals(codigoBanco, value.getCodigoBanco())){
                return value;
            }
        }
        return null;
    }

}
