package com.controller.security;

import com.controller.email.EnviaEmailController;
import com.orm.TokenRecuperaSenha;
import com.orm.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;

import java.time.LocalDateTime;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class RecuperarSenhaController {

    @Inject
    EnviaEmailController enviaEmailController;

    public void recuperarSenha(String dsEmail) {

        Usuario usuario = Usuario.find("dsEmail = ?1", dsEmail).firstResult();

        if (usuario == null) {
            return;
        }

        String token = UUID.randomUUID().toString();

        TokenRecuperaSenha tokenRecuperaSenha = new TokenRecuperaSenha(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(5),
                true,
                usuario
        );

        tokenRecuperaSenha.persist();

        String dsLink = "http://localhost:9000/recuperar-senha/verifica-token?token=" + token;

        enviaEmailController.enviaEmail(dsLink, usuario.getDsEmail());

    }

    public void verificarTokenRecuperacao(String dsToken) {

        TokenRecuperaSenha tokenRecuperaSenha = TokenRecuperaSenha.find("dsToken = ?1", dsToken).firstResult();

        if (tokenRecuperaSenha == null) {
            throw new BadRequestException("Nenhum token encontrado");
        }

        if (!tokenRecuperaSenha.getFgAtivo()){
            tokenRecuperaSenha.setFgAtivo(false);
            throw new BadRequestException("Token inativo");
        }

        if (LocalDateTime.now().isAfter(tokenRecuperaSenha.getDtExpiracao())){
            tokenRecuperaSenha.setFgAtivo(false);
            throw new BadRequestException("Token expirado");
        }

        tokenRecuperaSenha.setFgAtivo(false);
    }
}
