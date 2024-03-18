package com.dto.project.list;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@RegisterForReflection
public class ListMovimentoProjectDTO {

    private Long idMovimento;

    private Integer nrParcela;

    private Integer qtdParcelas;

    private String dsDescricao;

    private BigDecimal vlMovimento;

    private String dtMovimento;

    private String dtVencimento;

    private Boolean fgConciliarAutomatico;

    private String dsCategoria;

    private String dsCor;

    private String dsContaBancaria;

    private String dsBanco;

    public ListMovimentoProjectDTO(Long idMovimento, Integer nrParcela, Integer qtdParcelas, String dsDescricao,
                                   BigDecimal vlMovimento, LocalDate dtMovimento, LocalDate dtVencimento, Boolean fgConciliarAutomatico,
                                   String dsCategoria, String dsCor, String dsContaBancaria, String dsBanco) {
        this.idMovimento = idMovimento;
        this.nrParcela = nrParcela;
        this.qtdParcelas = qtdParcelas;
        this.dsDescricao = dsDescricao;
        this.vlMovimento = vlMovimento;
        this.dtMovimento = dtMovimento.toString();
        this.dtVencimento = dtVencimento.toString();
        this.fgConciliarAutomatico = fgConciliarAutomatico;
        this.dsCategoria = dsCategoria;
        this.dsCor = dsCor;
        this.dsContaBancaria = dsContaBancaria;
        this.dsBanco = dsBanco;
    }
}
