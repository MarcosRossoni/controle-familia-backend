package com.orm;

import com.enumeration.TipoContaBancaria;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "conta_bancaria")
@EqualsAndHashCode(callSuper = true)
@SequenceGenerator(name = "seq_bancaria", sequenceName = "seq_bancaria", allocationSize = 1)
public class ContaBancaria extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "seq_bancaria", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Integer idContaBancaria;

    @Column(name = "ds_descricao", nullable = false)
    private String dsDescricao;

    @Column(name = "ds_banco", nullable = false)
    private String dsBanco;

    @Column(name = "num_conta")
    private String numConta;

    @Column(name = "num_agencia")
    private String numAgencia;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "fg_conta_bancaria", nullable = false)
    private TipoContaBancaria fgContaBancaria;

    @Column(name = "vl_saldo_inicial", nullable = false)
    private BigDecimal vlSaldoIncial;

    @Column(name = "vl_saldo_atual", nullable = false)
    private BigDecimal vlSaldoAtual;

    @Column(name = "dt_cadastro", nullable = false, updatable = false)
    private LocalDateTime dtCadastro;

    @Column(name = "dt_ultima_movimentacao", nullable = false)
    private LocalDateTime dtUltimaMovimentacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_cadastro", nullable = false, updatable = false)
    private Usuario usuarioCadastro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_movimentacao", nullable = false)
    private Usuario usuarioMovimentacao;
}
