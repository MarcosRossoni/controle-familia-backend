package com.example.controlefamiliabackend.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Entity
@Table(name = "perfil")
public class Perfil implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private BigInteger id;

    @Column(name = "nome_perfil", length = 50)
    private String nome;

    @Override
    public String getAuthority() {
        return this.nome;
    }
}
