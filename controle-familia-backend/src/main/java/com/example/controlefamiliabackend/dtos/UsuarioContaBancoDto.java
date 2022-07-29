package com.example.controlefamiliabackend.dtos;

import lombok.Data;

import java.math.BigInteger;

@Data
public class UsuarioContaBancoDto {

    private BigInteger idUsuario;

    private String dsNome;

    public UsuarioContaBancoDto(BigInteger idUsuario, String dsNome) {
        this.idUsuario = idUsuario;
        this.dsNome = dsNome;
    }
}
