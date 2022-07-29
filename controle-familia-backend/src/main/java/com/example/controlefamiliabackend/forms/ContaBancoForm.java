package com.example.controlefamiliabackend.forms;

import com.example.controlefamiliabackend.models.ContaBancoModel;
import com.example.controlefamiliabackend.models.UsuarioModel;
import com.example.controlefamiliabackend.repositories.UsuarioRepository;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class ContaBancoForm {

    @NotNull
    private BigInteger idUsuario;

    @NotNull @NotEmpty
    private String codigoBanco;

    @NotNull @NotEmpty
    private String agencia;

    @NotNull @NotEmpty
    private String numConta;

    @NotNull
    private BigDecimal saldo;

    @NotNull
    private Integer tipoConta;

    public ContaBancoModel converter(UsuarioRepository usuarioRepository){
        UsuarioModel usuarioModel = usuarioRepository.getReferenceById(idUsuario);
        return new ContaBancoModel(usuarioModel, codigoBanco, agencia,
                numConta, saldo, tipoConta);
    }

    public ContaBancoForm() {
    }
}
