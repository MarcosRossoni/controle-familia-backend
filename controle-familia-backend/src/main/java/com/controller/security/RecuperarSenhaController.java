package com.controller.security;

import com.controller.email.EnviaEmailController;
import com.orm.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class RecuperarSenhaController {

    @Inject
    EnviaEmailController enviaEmailController;

    public void recuperarSenha(String email) {

        Usuario usuario = Usuario.find("dsEmail = ?1", email).firstResult();

        if (usuario == null) {
            return;
        }

        String token = UUID.randomUUID().toString();
        usuario.setDsTokenRecuperacao(HashingController.hashingSenha(token, usuario.getDsSalt()));

        enviaEmailController.enviaEmail(token, usuario.getDsEmail());

        usuario.persist();

    }
}
