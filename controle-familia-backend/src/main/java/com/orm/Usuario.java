package com.orm;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.type.TrueFalseConverter;

@Data
@Entity
@Table(name = "usuario")
@EqualsAndHashCode(callSuper = true)
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
public class Usuario extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "seq_usuario", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_usuario", nullable = false, updatable = false, unique = true)
    private Long idUsuario;

    @Column(name = "ds_nome", nullable = false, length = 200)
    private String dsNome;

    @Column(name = "ds_email", nullable = false, length = 200)
    private String dsEmail;

    @Column(name = "ds_telefone", nullable = false, length = 11)
    private String dsTelefone;

    @Column(name = "ds_senha", nullable = false, length = 20)
    private String dsSenha;

    @Column(name = "ds_salt", length = 36)
    private String dsSalt;

    @Basic
    @Convert(converter = TrueFalseConverter.class)
    @Column(name = "fg_ativo", nullable = false)
    private Boolean fgAtivo;

    @Column(name = "ds_endereco", nullable = false, length = 200)
    private String dsEndereco;

    @Column(name = "num_predial", nullable = false)
    private Long numPredial;

    @Column(name = "ds_bairro", nullable = false, length = 200)
    private String dsBairro;

    @Column(name = "ds_complemento", length = 200)
    private String dsComplemento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cidade", nullable = false)
    private Cidade cidade;
}
