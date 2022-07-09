package com.example.controlefamiliabackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "ususario")
public class UsuarioModel implements Serializable {

    @Id
    @Column(name = "idusuario")
    private Integer idUsuraio;

    @Column(name = "nome")
    private String dsNome;

    @Column(name = "dtnascimento")
    private Date dtNascimento;

    @Column(name = "cpf")
    private String dsCpf;

    @Column(name = "endereco")
    private String dsEndereco;

    @Column(name = "conjuge")
    private String dsConjuge;

    @Column(name = "dtcadastro")
    private LocalDateTime dtCadastro;

}