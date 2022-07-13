package com.example.controlefamiliabackend.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
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

    @NotBlank
    private Date dtNascimento;

    private String dsCpf;

    @NotBlank
    private String dsEndereco;
}
