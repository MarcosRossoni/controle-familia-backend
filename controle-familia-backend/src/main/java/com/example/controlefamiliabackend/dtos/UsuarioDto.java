package com.example.controlefamiliabackend.dtos;

import com.example.controlefamiliabackend.models.UsuarioModel;
import lombok.Data;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UsuarioDto {

    @NotNull
    private BigInteger idUsuario;
    @NotBlank
    private String dsEmail;

    private String dsTelefone;

    @NotBlank
    private String dsNome;

    @Past
    @NotNull
    private Date dtNascimento;

    private String dsCpf;

    @NotBlank
    private String dsEndereco;

    private LocalDateTime dtCadastro;

    public UsuarioDto() {
    }

    public UsuarioDto(UsuarioModel usuarioModel) {
        this.idUsuario = usuarioModel.getId();
        this.dsEmail = usuarioModel.getDsEmail();
        this.dsTelefone = usuarioModel.getDsTelefone();
        this.dsNome = usuarioModel.getDsNome();
        this.dtNascimento = usuarioModel.getDtNascimento();
        this.dsCpf = usuarioModel.getDsCpf();
        this.dsEndereco = usuarioModel.getDsEndereco();
        this.dtCadastro = usuarioModel.getDtCadastro();
    }


    public static UsuarioDto converter(UsuarioModel usuarioModel){
        return new UsuarioDto(usuarioModel);
    }

    public static Page<UsuarioDto> converterList(Page<UsuarioModel> usuarioList){
        return usuarioList.map(UsuarioDto::new);
    }
}
