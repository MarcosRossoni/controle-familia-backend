package com.example.controlefamiliabackend.dtos;

import lombok.Data;
import org.springframework.core.SpringVersion;
import org.springframework.http.ResponseEntity;

@Data
public class TokenDto {

    private String token;
    private String tipo;

    public TokenDto(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }
}
