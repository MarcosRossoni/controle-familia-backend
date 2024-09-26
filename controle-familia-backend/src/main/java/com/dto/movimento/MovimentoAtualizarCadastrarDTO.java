package com.dto.movimento;

import com.dto.contabancaria.ContaBancariaDTO;
import com.dto.categoria.CategoriaDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MovimentoAtualizarCadastrarDTO {

    private Long idMovimento;

    private Integer nrParcela;

    private String dsDescricao;

    private Integer qtdParcelas;

    private BigDecimal vlMovimento;

    private String dtMovimento;

    private String dtVencimento;

    private Boolean fgConciliarAutomatico;

    private Boolean fgValorParcela;

    private Integer fgTipoMovimento;

    private Integer fgSituacaoMovimento;

    private CategoriaDTO categoria;

    private ContaBancariaDTO contaBancaria;
}
