package com.orm;

import com.enumeration.TipoMovimento;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "categoria")
@EqualsAndHashCode(callSuper = true)
@SequenceGenerator(name = "seq_categoria", sequenceName = "seq_categoria", allocationSize = 1)
public class Categoria extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "seq_categoria", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_categoria", nullable = false, updatable = false)
    private Long idCategoria;

    @Column(name = "ds_descricao", nullable = false)
    private String dsDescricao;

    @Column(name = "ds_cor", nullable = false)
    private String dsCor;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "fg_tipo", nullable = false)
    private TipoMovimento fgTipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}
