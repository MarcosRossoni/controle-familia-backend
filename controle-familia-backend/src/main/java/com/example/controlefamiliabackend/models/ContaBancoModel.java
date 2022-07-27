package com.example.controlefamiliabackend.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotBlank
    @Column(nullable = false, length = 250)
    private String codigoBanco;

    @NotBlank
    @Column(nullable = false, length = 250)
    private String agencia;

    @NotBlank
    @Column(nullable = false, length = 250)
    private String numConta;

    @NotNull
    @Column(nullable = false)
    private BigDecimal saldo;

    @NotBlank
    @Column(nullable = false)
    private String tipoConta;

    @Column
    private LocalDateTime dtCadastro = LocalDateTime.now();

    public ContaBancoModel(UsuarioModel titular, String codigoBanco, String agencia,
                           String numConta, BigDecimal saldo, String tipoConta) {
        this.titular = titular;
        this.codigoBanco = codigoBanco;
        this.agencia = agencia;
        this.numConta = numConta;
        this.saldo = saldo;
        this.tipoConta = tipoConta;
    }

    public ContaBancoModel() {
    }
}