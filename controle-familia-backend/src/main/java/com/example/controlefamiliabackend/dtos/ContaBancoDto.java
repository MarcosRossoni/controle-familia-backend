package com.example.controlefamiliabackend.dtos;

import com.example.controlefamiliabackend.models.UsuarioModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ContaBancoDto {

    @NotBlank
    private UsuarioModel titular;

    @NotBlank
    private String dsBanco;

    @NotBlank
    private String codigoBanco;

    @NotBlank
    private String agencia;

    @NotBlank
    private String numConta;

    @NotNull
    private BigDecimal saldo;

    @NotBlank
    private String tipoConta;
}
