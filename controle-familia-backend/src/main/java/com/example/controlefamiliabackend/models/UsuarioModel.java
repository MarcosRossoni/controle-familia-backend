package com.example.controlefamiliabackend.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "ususario")
public class UsuarioModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 25)
    private String dsEmail;

    @Column(nullable = false, unique = true, length = 25)
    private String dsSenha;

    @Column(length = 25)
    private String dsTelefone;

    @Column(nullable = false, length = 255)
    private String dsNome;

    @Column(nullable = false)
    private Date dtNascimento;

    @Column(length = 25)
    private String dsCpf;

    @Column(nullable = false, length = 25)
    private String dsEndereco;

    @Column(nullable = false)
    private LocalDateTime dtCadastro;

}