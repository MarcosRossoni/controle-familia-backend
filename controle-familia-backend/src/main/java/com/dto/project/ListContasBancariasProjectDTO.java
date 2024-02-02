package com.dto.project;

import com.enumeration.TipoContaBancaria;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@RegisterForReflection
public class ListContasBancariasProjectDTO {

    private Integer idContaBancaria;

    private String dsDescricao;

    private String dsBanco;

    private String numConta;

    private String numAgencia;

    private Integer fgContaBancaria;

    private BigDecimal vlSaldoIncial;

    private BigDecimal vlSaldoAtual;

    private String dtUltimaMovimentacao;

    public ListContasBancariasProjectDTO(Integer idContaBancaria, String dsDescricao, String dsBanco, String numConta,
                                         String numAgencia, TipoContaBancaria fgContaBancaria, BigDecimal vlSaldoIncial,
                                         BigDecimal vlSaldoAtual, LocalDateTime dtUltimaMovimentacao) {
        this.idContaBancaria = idContaBancaria;
        this.dsDescricao = dsDescricao;
        this.dsBanco = dsBanco;
        this.numConta = numConta;
        this.numAgencia = numAgencia;
        this.fgContaBancaria = fgContaBancaria.ordinal();
        this.vlSaldoIncial = vlSaldoIncial;
        this.vlSaldoAtual = vlSaldoAtual;
        this.dtUltimaMovimentacao = dtUltimaMovimentacao.toString();
    }
}
