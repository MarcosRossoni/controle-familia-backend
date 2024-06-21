package com.orm;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.type.TrueFalseConverter;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "token_recupera_senha")
@EqualsAndHashCode(callSuper=true)
@SequenceGenerator(name = "seq_token_recupera_senha", sequenceName = "seq_token_recupera_senha", allocationSize = 1)
public class TokenRecuperaSenha extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "seq_token_recupera_senha", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_token", nullable = false, updatable = false, unique = true)
    private Long idToken;

    @Column(name = "ds_token", nullable = false, length = 200)
    private String dsToken;

    @Column(name = "dt_geracao_token", nullable = false)
    private LocalDateTime dtGeracaoToken;

    @Column(name = "dt_expiracao", nullable = false)
    private LocalDateTime dtExpiracao;

    @Basic
    @Convert(converter = TrueFalseConverter.class)
    @Column(name = "fg_ativo", nullable = false)
    private Boolean fgAtivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public TokenRecuperaSenha() {
    }

    public TokenRecuperaSenha(String dsToken, LocalDateTime dtGeracaoToken, LocalDateTime dtExpiracao,
                              Boolean fgAtivo, Usuario usuario) {
        this.dsToken = dsToken;
        this.dtGeracaoToken = dtGeracaoToken;
        this.dtExpiracao = dtExpiracao;
        this.fgAtivo = fgAtivo;
        this.usuario = usuario;
    }
}
