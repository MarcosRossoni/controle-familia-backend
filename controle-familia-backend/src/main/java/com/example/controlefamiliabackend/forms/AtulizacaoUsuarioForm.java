package com.example.controlefamiliabackend.forms;

import com.example.controlefamiliabackend.models.UsuarioModel;
import com.example.controlefamiliabackend.repositories.UsuarioRepository;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

@Data
public class AtulizacaoUsuarioForm {

    @NotNull
    @NotEmpty
    private String dsTelefone;

    @NotNull @NotEmpty
    private String dsNome;

    @NotNull @NotEmpty
    private String dsEndereco;

    @NotNull
    private Date dtNascimento;

    @NotNull @NotEmpty
    private String dsCpf;

    public UsuarioModel atualizar(BigInteger id, UsuarioRepository usuarioRepository) {
        UsuarioModel usuarioModel = usuarioRepository.getReferenceById(id);
        usuarioModel.setDsTelefone(this.dsTelefone);
        usuarioModel.setDsNome(this.dsNome);
        usuarioModel.setDsEndereco(this.dsEndereco);
        usuarioModel.setDtNascimento(this.dtNascimento);
        usuarioModel.setDsCpf(this.dsCpf);
        return usuarioModel;
    }
}
