package com.example.controlefamiliabackend.dtos;

import com.example.controlefamiliabackend.models.ContaBancoModel;
import com.example.controlefamiliabackend.models.UsuarioModel;
import com.example.controlefamiliabackend.repositories.UsuarioRepository;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

@Data
public class ContaBancoDto {

    @NotNull
    private Integer idUsuario;

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

    public ContaBancoModel converter(UsuarioRepository usuarioRepository){
       UsuarioModel titular = usuarioRepository.getReferenceById(idUsuario);
        return new ContaBancoModel(titular, codigoBanco, agencia, numConta, saldo, tipoConta);
    }
}
