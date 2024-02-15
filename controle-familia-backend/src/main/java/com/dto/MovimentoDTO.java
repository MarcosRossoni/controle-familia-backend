package com.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MovimentoDTO {

    private Long idMovimento;

    private String dsDescricao;

    private Integer qtdParcelas;

    private BigDecimal vlMovimento;

    private String dtMovimento;

    private String dtVencimento;

    private String dtCadastro;

    private String dtAlteracao;

    private Boolean fgConciliarAutomatico;

    private Integer fgTipoMovimento;

    private CategoriaDTO categoria;

    private UsuarioDTO usuario;

    private ContaBancariaDTO contaBancaria;
}
