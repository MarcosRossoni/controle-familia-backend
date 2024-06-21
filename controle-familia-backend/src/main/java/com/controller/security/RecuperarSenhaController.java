package com.controller.security;

import com.controller.email.EnviaEmailController;
import com.orm.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;

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
        usuario.setDsTokenRecuperacao(HashingController.hashingSenha(token, usuario.getDsSalt()));

        enviaEmailController.enviaEmail(token, usuario.getDsEmail());

        usuario.persist();

    }

    public void verificarTokenRecuperacao(String dsEmail, String dsToken) {

        Usuario usuario = Usuario.find("dsEmail = ?1", dsEmail).firstResult();

        if (usuario == null) {
            return;
        }

        boolean equalsTokenRefector = HashingController.isEqualsTokenRefector(usuario, dsToken);

        if (!equalsTokenRefector) {
            throw new BadRequestException("Token invalido");
        }

        usuario.setDsTokenRecuperacao(null);
        usuario.persist();
    }
}
