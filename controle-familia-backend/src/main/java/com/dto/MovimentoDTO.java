package com.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MovimentoDTO {

    private Long idMovimento;

    private String dsDescricao;

    private BigDecimal vlMovimento;

    private String dtMovimento;

    private String dtCadastro;

    private String dtAlteracao;

    private Integer fgTipoMovimento;

    private UsuarioDTO usuario;
}
