package com.controller.security;

import com.controller.email.EnviaEmailController;
import com.orm.TokenRecuperaSenha;
import com.orm.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.LocalDateTime;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class RecuperarSenhaController {

    @Inject
    EnviaEmailController enviaEmailController;

    @ConfigProperty(name = "URL_RECUPERAR_SENHA")
    String URL_RECUPERAR_SENHA;

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

        String dsLink = URL_RECUPERAR_SENHA + token;

        enviaEmailController.enviaEmail(dsLink, usuario.getDsEmail(), usuario.getDsNome());

    }

    public void verificarTokenRecuperacao(String dsToken, String dsSenha) {

        TokenRecuperaSenha tokenRecuperaSenha = TokenRecuperaSenha.find("dsToken = ?1", dsToken).firstResult();

        if (tokenRecuperaSenha == null) {
            throw new BadRequestException("Nenhum token encontrado");
        }

        if (!tokenRecuperaSenha.getFgAtivo()){
            tokenRecuperaSenha.setFgAtivo(false);
            tokenRecuperaSenha.persist();
            throw new BadRequestException("Token inativo");
        }

        if (LocalDateTime.now().isAfter(tokenRecuperaSenha.getDtExpiracao())){
            tokenRecuperaSenha.setFgAtivo(false);
            tokenRecuperaSenha.persist();
            throw new BadRequestException("Token expirado");
        }

        Usuario usuario = Usuario.findById(tokenRecuperaSenha.getUsuario().getIdUsuario());
        String newSenha = HashingController.hashingSenha(
                dsSenha,
                usuario.getDsSalt());
        usuario.setDsSenha(newSenha);
        usuario.persist();

        tokenRecuperaSenha.setFgAtivo(false);
        tokenRecuperaSenha.persist();
    }
}
