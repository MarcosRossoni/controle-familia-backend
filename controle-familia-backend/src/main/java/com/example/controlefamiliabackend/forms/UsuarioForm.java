package com.example.controlefamiliabackend.forms;

import com.example.controlefamiliabackend.models.UsuarioModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UsuarioForm {

    @NotNull @NotEmpty
    private String dsEmail;

    @NotNull @NotEmpty
    private String dsSenha;

    @NotNull @NotEmpty
    private String dsTelefone;

    @NotNull @NotEmpty
    private String dsNome;

    @NotNull
    private Date dtNascimento;

    @NotNull @NotEmpty
    private String dsCpf;

    @NotNull @NotEmpty
    private String dsEndereco;

    public UsuarioModel converter() {
        return new UsuarioModel(dsEmail, dsSenha, dsTelefone,
                dsNome, dtNascimento, dsCpf, dsEndereco);
    }
}
