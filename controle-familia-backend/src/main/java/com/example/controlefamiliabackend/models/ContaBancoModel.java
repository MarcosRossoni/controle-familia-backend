package com.example.controlefamiliabackend.models;

import com.example.controlefamiliabackend.enuns.CodigoBancos;
import com.example.controlefamiliabackend.enuns.TipoContaBanco;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "conta_bancaria")
public class ContaBancoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_conta")
    private BigInteger idContaBancaria;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private UsuarioModel titular;

    @Column(nullable = false, length = 250)
    private CodigoBancos codigoBanco;

    @Column(nullable = false, length = 250)
    private String agencia;

    @Column(nullable = false, length = 250)
    private String numConta;

    @Column(nullable = false)
    private BigDecimal saldo;

    @Column(nullable = false)
    private TipoContaBanco tipoConta;

    @Column
    private LocalDateTime dtCadastro = LocalDateTime.now();

    public ContaBancoModel(UsuarioModel titular, String codigoBanco, String agencia,
                           String numConta, BigDecimal saldo, String tipoConta) {
        this.titular = titular;
        this.codigoBanco = CodigoBancos.shortName(codigoBanco);
        this.agencia = agencia;
        this.numConta = numConta;
        this.saldo = saldo;
        this.tipoConta = TipoContaBanco.valueOf(tipoConta);
    }

    public ContaBancoModel() {
    }
}