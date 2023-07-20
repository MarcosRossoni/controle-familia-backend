package com.orm;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "cidade")
@EqualsAndHashCode(callSuper = true)
@SequenceGenerator(name = "seq_cidade", sequenceName = "seq_cidade", allocationSize = 1)
public class Cidade extends PanacheEntityBase {

    @Id
    @Column(name = "id_cidade", nullable = false, updatable = false, unique = true)
    private Long idCidade;

    @Column(name = "ds_nome")
    private String dsNome;

    @Column(name = "ds_sigla_estado")
    private String dsSiglaEstado;

    @Column(name = "ds_nome_estado")
    private String dsNomeEstado;

    @Column(name = "ds_regiao")
    private String dsRegiao;
}
