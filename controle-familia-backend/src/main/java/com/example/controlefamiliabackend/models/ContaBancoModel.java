package com.example.controlefamiliabackend.models;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;

@Data
@Entity
@Table(name = "conta_bancaria")
public class ContaBancoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_conta")
    private Integer idContaBancaria;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private UsuarioModel titular;

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

//    @Column
//    private LocalDateTime dtCadastro;

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