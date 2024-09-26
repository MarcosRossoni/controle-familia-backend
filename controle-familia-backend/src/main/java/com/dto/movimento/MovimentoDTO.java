package com.dto.movimento;

import com.dto.categoria.CategoriaDTO;
import com.dto.contabancaria.ContaBancariaDTO;
import com.dto.usuario.UsuarioDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MovimentoDTO {

    private Long idMovimento;

    private Integer nrParcela;

    private String dsDescricao;

    private Integer qtdParcelas;

    private BigDecimal vlMovimento;

    private String dtMovimento;

    private String dtVencimento;

    private String dtCadastro;

    private String dtAlteracao;

    private Boolean fgConciliarAutomatico;

    private Integer fgTipoMovimento;

    private Integer fgSituacaoMovimento;

    private CategoriaDTO categoria;

    private UsuarioDTO usuario;

    private ContaBancariaDTO contaBancaria;
}
