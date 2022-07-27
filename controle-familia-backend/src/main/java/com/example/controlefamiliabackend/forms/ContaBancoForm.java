package com.example.controlefamiliabackend.forms;

import com.example.controlefamiliabackend.models.ContaBancoModel;
import com.example.controlefamiliabackend.models.UsuarioModel;
import com.example.controlefamiliabackend.repositories.ContaBancoRepository;
import com.example.controlefamiliabackend.repositories.UsuarioRepository;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class ContaBancoForm {

    private BigInteger idUsuario;

    private String codigoBanco;

    private String agencia;

    private String numConta;

    private BigDecimal saldo;

    private String tipoConta;

    public ContaBancoModel converter(UsuarioRepository usuarioRepository){
        UsuarioModel usuarioModel = usuarioRepository.getReferenceById(idUsuario);
        return new ContaBancoModel(usuarioModel, codigoBanco, agencia,
                numConta, saldo, tipoConta);
    }
}
