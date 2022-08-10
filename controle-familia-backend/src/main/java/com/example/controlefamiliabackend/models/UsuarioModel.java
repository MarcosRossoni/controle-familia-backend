package com.example.controlefamiliabackend.models;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "usuario")
public class UsuarioModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private BigInteger id;

    @Column(name= "email_usuario", nullable = false, unique = true, length = 50)
    private String dsEmail;

    @Column(name= "senha", nullable = false, length = 60)
    private String dsSenha;

    @Column(name= "telefone", length = 25)
    private String dsTelefone;

    @Column(name= "nome", nullable = false, length = 255)
    private String dsNome;

    @Column(name= "dt_nascimento", nullable = false)
    private Date dtNascimento;

    @CPF
    @Column(name= "cpf", length = 25)
    private String dsCpf;

    @Column(name= "endereco", nullable = false, length = 25)
    private String dsEndereco;

    @Column(name= "dt_cadastro", nullable = false)
    private LocalDateTime dtCadastro = LocalDateTime.now();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();

    public UsuarioModel() {
    }

    public UsuarioModel(String dsEmail, String dsSenha, String dsTelefone,
                        String dsNome, Date dtNascimento, String dsCpf, String dsEndereco) {
        this.dsEmail = dsEmail;
        this.dsSenha = dsSenha;
        this.dsTelefone = dsTelefone;
        this.dsNome = dsNome;
        this.dtNascimento = dtNascimento;
        this.dsCpf = dsCpf;
        this.dsEndereco = dsEndereco;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.dsSenha;
    }

    @Override
    public String getUsername() {
        return this.dsEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}