package com.orm;

import com.enumeration.TipoMovimento;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    @GeneratedValue(generator = "seq_movimento", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_movimento")
    private Long idMovimento;

    @Column(name = "ds_descricao", nullable = false)
    private String dsDescricao;

    @Column(name = "vl_movimento")
    private BigDecimal vlMovimento;

    @Column(name = "dt_movimento")
    private LocalDate dtMovimento;

    @Column(name = "dt_cadastro")
    private LocalDateTime dtCadastro;

    @Column(name = "dt_alteracao")
    private LocalDateTime dtAlteracao;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "fg_tipo_movimento")
    private TipoMovimento fgTipoMovimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
