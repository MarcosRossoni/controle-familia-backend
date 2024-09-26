package com.dto.usuario;

import com.dto.cidade.CidadeDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UsuarioDTO {

    private Long idUsuario;

    @NotNull(message = "usuario.dsNome.notNull")
    @Length(max = 200, message = "usuario.dsNome.length")
    private String dsNome;

    @NotNull(message = "usuario.dsEmail.notNull")
    @Length(max = 200, message = "usuario.dsEmail.length")
    private String dsEmail;

    @NotNull(message = "usuario.dsTelefone.notNull")
    @Length(max = 11, message = "usuario.dsTelefone.length")
    private String dsTelefone;

    @NotNull(message = "usuario.dtNascimento.notNull")
    private String dtNascimento;

    @NotNull(message = "usuario.dsCpf.notNull")
    private String dsCpf;

    @NotNull(message = "usuario.dsSenha.notNull")
    @Length(min = 8, max = 20, message = "usuario.dsSenha.length")
    private String dsSenha;

    private Boolean fgAtivo;

    private String dtCadastro;

    private String dtAlteracao;

    @NotNull(message = "usuario.dsEndereco.notNull")
    @Length(max = 200, message = "usuario.dsEndereco.length")
    private String dsEndereco;

    @NotNull(message = "usuario.numPredial.notNull")
    private Long numPredial;

    @NotNull(message = "usuario.dsBairro.notNull")
    @Length(max = 200, message = "usuario.dsBairro.length")
    private String dsBairro;

    @Length(max = 200, message = "usuario.dsComplemento.length")
    private String dsComplemento;

    @NotNull(message = "usuario.cidade.notNull")
    private CidadeDTO cidade;

}
