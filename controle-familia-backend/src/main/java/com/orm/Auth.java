package com.orm;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.type.TrueFalseConverter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "auth")
@EqualsAndHashCode(callSuper = true)
public class Auth extends PanacheEntityBase implements Serializable {

    @Id
    @Column(name = "access_token", nullable = false, updatable = false, unique = true, length = 36)
    private String accessToken;

    @Column(name = "refresh_token", nullable = false, updatable = false, unique = true, length = 36)
    private String refreshToken;

    @Column(name = "dt_movimento", nullable = false, updatable = false)
    private LocalDateTime dtMovimento;

    @Column(name = "dt_expiracao", nullable = false, updatable = false)
    private LocalDateTime dtExpiracao;

    @Basic
    @Convert(converter = TrueFalseConverter.class)
    @Column(name = "fg_valido", nullable = false, updatable = false, length = 1)
    private boolean fgValido;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false, updatable = false)
    private Usuario usuario;

    public Auth() {
    }

    public Auth(Usuario usuario) {
        this.accessToken = UUID.randomUUID().toString();
        this.refreshToken = UUID.randomUUID().toString();
        this.dtMovimento = LocalDateTime.now();
        this.dtExpiracao = this.dtMovimento.plusHours(1);
        this.fgValido = true;
        this.usuario = usuario;
    }
}
