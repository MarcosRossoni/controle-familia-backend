package com.orm;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.type.TrueFalseConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(name = "dt_nascimento", nullable = false)
    private LocalDate dtNascimento;

    @Column(name = "ds_cpf", nullable = false)
    private String dsCpf;

    @Column(name = "ds_senha", nullable = false, length = 250)
    private String dsSenha;

    @Column(name = "ds_salt", length = 36)
    private String dsSalt;

    @Basic
    @Convert(converter = TrueFalseConverter.class)
    @Column(name = "fg_ativo", nullable = false)
    private Boolean fgAtivo;

    @Column(name = "dt_cadastro", nullable = false)
    private LocalDateTime dtCadastro;

    @Column(name = "dt_alteracao", nullable = false)
    private LocalDateTime dtAlteracao;

    @Column(name = "ds_endereco", nullable = false, length = 200)
    private String dsEndereco;

    @Column(name = "num_predial", nullable = false)
    private Long numPredial;

    @Column(name = "ds_bairro", nullable = false, length = 200)
    private String dsBairro;

    @Column(name = "ds_complemento", length = 200)
    private String dsComplemento;

    @Column(name = "ds_token_recuperacao", length = 200)
    private String dsTokenRecuperacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cidade", nullable = false)
    private Cidade cidade;
}
