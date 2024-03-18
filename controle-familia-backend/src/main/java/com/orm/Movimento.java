package com.orm;

import com.enumeration.TipoMovimento;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.type.TrueFalseConverter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "movimento")
@EqualsAndHashCode(callSuper = true)
@SequenceGenerator(name = "seq_movimento", sequenceName = "seq_movimento", allocationSize = 1)
public class Movimento extends PanacheEntityBase {

    @Id
//    @GeneratedValue(generator = "seq_movimento", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_movimento", nullable = false, updatable = false)
    private Long idMovimento;

    @Id
    @Column(name = "qtd_parcelas", nullable = false)
    private Integer nrParcela;

    @Column(name = "ds_descricao", nullable = false)
    private String dsDescricao;

    @Column(name = "vl_movimento", nullable = false)
    private BigDecimal vlMovimento;

    @Column(name = "dt_movimento", nullable = false)
    private LocalDate dtMovimento;

    @Column(name = "dt_vencimento", nullable = false)
    private LocalDate dtVencimento;

    @Column(name = "dt_cadastro", nullable = false, updatable = false)
    private LocalDateTime dtCadastro;

    @Column(name = "dt_alteracao", nullable = false)
    private LocalDateTime dtAlteracao;

    @Column(name = "qtd_total_parcelas", nullable = false)
    private Integer qtdTotalParcelas;

    @Basic
    @Convert(converter = TrueFalseConverter.class)
    @Column(name = "fg_conciliar_automatico", nullable = false)
    private Boolean fgConciliarAutomatico;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "fg_tipo_movimento", nullable = false)
    private TipoMovimento fgTipoMovimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conta_bancaria", nullable = false)
    private ContaBancaria contaBancaria;

}
