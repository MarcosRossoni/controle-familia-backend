package com.example.controlefamiliabackend.forms;

import com.example.controlefamiliabackend.dtos.UsuarioContaBancoDto;
import com.example.controlefamiliabackend.enuns.TipoContaBanco;
import com.example.controlefamiliabackend.models.ContaBancoModel;
import com.example.controlefamiliabackend.models.UsuarioModel;
import com.example.controlefamiliabackend.repositories.ContaBancoRepository;
import com.example.controlefamiliabackend.repositories.UsuarioRepository;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
public class AtualizacaoContaBancoForm {

    @NotNull
    private BigInteger idUsuario;

    @NotNull
    private TipoContaBanco tipoConta;

    public AtualizacaoContaBancoForm() {
    }

    public ContaBancoModel converterAtt(UsuarioRepository usuarioRepository){
        UsuarioModel usuarioModel = usuarioRepository.getReferenceById(idUsuario);
        return new ContaBancoModel(usuarioModel);
    }

    public ContaBancoModel atualizar(BigInteger id, ContaBancoRepository contaBancoRepository,
                                     UsuarioRepository usuarioRepository){
        ContaBancoModel contaBancoModel = contaBancoRepository.getReferenceById(id);
        contaBancoModel.setTitular(this.converterAtt(usuarioRepository).getTitular());
        contaBancoModel.setTipoConta(this.tipoConta);

        return contaBancoModel;
    }
}
