package com.dto.movimento.project;

import com.dto.categoria.CategoriaDTO;
import com.dto.contabancaria.ContaBancariaDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

import java.math.BigDecimal;

@RegisterForReflection
@Data
public class MovimentoProjectDTO {

    private Long idMovimento;

    private Integer nrParcela;

    private String dsDescricao;

    private Integer qtdParcelas;

    private BigDecimal vlMovimento;

    private String dtMovimento;

    private String dtVencimento;

    private Boolean fgConciliarAutomatico;

    private Integer fgTipoMovimento;

    private CategoriaDTO categoria;

    private ContaBancariaDTO contaBancaria;

    public MovimentoProjectDTO(Long idMovimento, Integer nrParcela, String dsDescricao, Integer qtdParcelas,
                               BigDecimal vlMovimento, String dtMovimento, String dtVencimento, Boolean fgConciliarAutomatico,
                               Integer fgTipoMovimento, CategoriaDTO categoria, ContaBancariaDTO contaBancaria) {
        this.idMovimento = idMovimento;
        this.nrParcela = nrParcela;
        this.dsDescricao = dsDescricao;
        this.qtdParcelas = qtdParcelas;
        this.vlMovimento = vlMovimento;
        this.dtMovimento = dtMovimento;
        this.dtVencimento = dtVencimento;
        this.fgConciliarAutomatico = fgConciliarAutomatico;
        this.fgTipoMovimento = fgTipoMovimento;
        this.categoria = categoria;
        this.contaBancaria = contaBancaria;
    }
}
