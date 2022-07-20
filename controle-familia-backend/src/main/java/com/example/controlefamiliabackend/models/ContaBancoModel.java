package com.example.controlefamiliabackend.models;

import com.example.controlefamiliabackend.enuns.CodigoBancos;
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
    private BigInteger idContaBancaria;

    @ManyToOne
    private UsuarioModel titular;

    @Column(nullable = false, length = 250)
    private String dsBanco;

    @Column(nullable = false, length = 250)
    private String codigoBanco;

    @Column(nullable = false, length = 250)
    private String agencia;

    @Column(nullable = false, length = 250)
    private String numConta;

    @Column(nullable = false)
    private BigDecimal saldo;

    @Column(nullable = false)
    private String tipoConta;

    @Column
    private LocalDateTime dtCadastro;
}
