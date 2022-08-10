package com.example.controlefamiliabackend.forms;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Data
public class LoginForm {

    private String dsEmail;
    private String dsSenha;


    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(dsEmail, dsSenha);
    }
}
