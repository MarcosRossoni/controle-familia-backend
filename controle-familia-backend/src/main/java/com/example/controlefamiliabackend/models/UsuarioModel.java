package com.example.controlefamiliabackend.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@Entity
@Table(name = "ususario")
public class UsuarioModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(nullable = false, unique = true, length = 50)
    private String dsEmail;

    @Column(nullable = false, length = 25)
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