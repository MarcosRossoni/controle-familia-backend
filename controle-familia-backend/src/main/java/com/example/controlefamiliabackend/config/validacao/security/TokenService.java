package com.example.controlefamiliabackend.config.validacao.security;

import com.example.controlefamiliabackend.models.UsuarioModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;

@Service
public class TokenService {

    @Value("${controle.jwt.expiration}")
    private String expiration;

    @Value("${controle.jwt.secret}")
    private String secret;


    public String gerarToken(Authentication authentication){
        UsuarioModel logado = (UsuarioModel) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API Controle Familia")
                .setSubject(logado.getId().toString())
                .setIssuedAt(hoje). setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public BigInteger getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return BigInteger.valueOf(Long.parseLong(claims.getSubject()));
    }
}
