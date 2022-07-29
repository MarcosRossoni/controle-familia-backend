package com.example.controlefamiliabackend.dtos;

import com.example.controlefamiliabackend.models.ContaBancoModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ContaBancoDto {

    @NotNull
    private BigInteger idContaBancaria;

    private UsuarioContaBancoDto usuario;

    @NotBlank
    private String codigoBanco;

    @NotBlank
    private String agencia;

    @NotBlank
    private String numConta;

    @NotNull
    private BigDecimal saldo;

    @NotBlank
    private Integer tipoConta;

    public ContaBancoDto() {
    }

    public ContaBancoDto(ContaBancoModel contaBancoModel) {
        this.idContaBancaria = contaBancoModel.getIdContaBancaria();
        this.usuario = new UsuarioContaBancoDto(contaBancoModel.getTitular().getId(),
                            contaBancoModel.getTitular().getDsNome());
        this.codigoBanco = contaBancoModel.getCodigoBanco().getCodigoBanco();
        this.agencia = contaBancoModel.getAgencia();
        this.numConta = contaBancoModel.getNumConta();
        this.saldo = contaBancoModel.getSaldo();
        this.tipoConta = contaBancoModel.getTipoConta().ordinal();
    }

    public static List<ContaBancoDto> converterList(List<ContaBancoModel> contaBancoList) {
        return contaBancoList.stream().map(ContaBancoDto::new).collect(Collectors.toList());

    }

    public static ContaBancoDto converter(ContaBancoModel contaBancoModel) {
        return new ContaBancoDto(contaBancoModel);
    }
}
