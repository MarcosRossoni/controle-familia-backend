package com.dto.contabancaria;

import com.dto.usuario.UsuarioDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContaBancariaDTO {

    private Integer idContaBancaria;

    private String dsDescricao;

    private String dsBanco;

    private String numConta;

    private String numAgencia;

    private Integer fgContaBancaria;

    private BigDecimal vlSaldoIncial;

    private BigDecimal vlSaldoAtual;

    private String dtUltimaMovimentacao;

    private Boolean fgAtiva;

    private UsuarioDTO usuarioCadastro;

    private UsuarioDTO usuarioMovimentacao;
}
