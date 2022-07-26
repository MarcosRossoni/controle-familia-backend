package com.example.controlefamiliabackend.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


@Data
@Entity
@Table(name = "usuario")
public class UsuarioModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name= "email_usuario", nullable = false, unique = true, length = 50)
    private String dsEmail;

    @Column(name= "senha", nullable = false, length = 25)
    private String dsSenha;

    @Column(name= "telefone", length = 25)
    private String dsTelefone;

    @Column(name= "nome", nullable = false, length = 255)
    private String dsNome;

    @Column(name= "dt_nascimento", nullable = false)
    private Date dtNascimento;

    @Column(name= "cpf", length = 25)
    private String dsCpf;

    @Column(name= "endereco", nullable = false, length = 25)
    private String dsEndereco;

    @Column(name= "dt_cadastro", nullable = false)
    private LocalDateTime dtCadastro = LocalDateTime.now();

}