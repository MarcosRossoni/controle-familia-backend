package com.example.controlefamiliabackend.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
public class UsuarioDto {

    @NotBlank
    private String dsEmail;

    @NotBlank
    private String dsSenha;

    private String dsTelefone;

    @NotBlank
    private String dsNome;

    @Past
    @NotNull
    private Date dtNascimento;

    private String dsCpf;

    @NotBlank
    private String dsEndereco;

    public UsuarioDto(String dsEmail, String dsTelefone,
                      String dsNome, Date dtNascimento, String dsCpf, String dsEndereco) {
        this.dsEmail = dsEmail;
        this.dsTelefone = dsTelefone;
        this.dsNome = dsNome;
        this.dtNascimento = dtNascimento;
        this.dsCpf = dsCpf;
        this.dsEndereco = dsEndereco;
    }
}
