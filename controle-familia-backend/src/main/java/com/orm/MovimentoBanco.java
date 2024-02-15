package com.orm;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.type.TrueFalseConverter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "movimento_banco")
@EqualsAndHashCode(callSuper = true)
@SequenceGenerator(name = "seq_movimento_banco", sequenceName = "seq_movimento_banco", allocationSize = 1)
public class MovimentoBanco extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(generator = "seq_movimento_banco", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_movimento", nullable = false, updatable = false)
    private Long idMovimentoParcela;

    @Id
    @Column(name = "nr_parcela", nullable = false)
    private Integer nrParcela;

    @Column(name = "dt_vencimento", nullable = false)
    private LocalDate dtVencimento;

    @Basic
    @Convert(converter = TrueFalseConverter.class)
    @Column(name = "fg_conciliar", nullable = false)
    private Boolean fgConciliar;

    @Column(name = "vl_parcela", nullable = false)
    private BigDecimal vlParcela;

    public MovimentoBanco() {
    }

    public MovimentoBanco(Long idMovimentoParcela, Integer nrParcela, LocalDate dtVencimento, Boolean fgConciliar,
                          BigDecimal vlParcela) {
        this.idMovimentoParcela = idMovimentoParcela;
        this.nrParcela = nrParcela;
        this.dtVencimento = dtVencimento;
        this.fgConciliar = fgConciliar;
        this.vlParcela = vlParcela;
    }
}
