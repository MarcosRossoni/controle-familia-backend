package com.controller.security;

import com.controller.cache.SessionCache;
import com.dto.auth.AuthDTO;
import com.orm.Auth;
import com.orm.Usuario;
import io.quarkus.security.UnauthorizedException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.ConfigProvider;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@Transactional
@ApplicationScoped
public class AuthController {

    public AuthDTO generateToken(String authorization, String username, String password) {

        validarAuthorization(authorization);
        username = decode(username);
        password = decode(password);

        Optional<Usuario> usuario = Usuario.find("UPPER(dsEmail)", username.toUpperCase()).firstResultOptional();
        if (usuario.isEmpty() || !HashingController.isEquals(usuario.get(), password)) {
            throw new UnauthorizedException("Usuario ou Senha incorreta");
        }

        final Auth auth = new Auth(usuario.get());
        auth.persist();

        return new AuthDTO(auth.getAccessToken(), auth.getRefreshToken(), auth.getDtExpiracao());

    }

    public AuthDTO refreshToken(String authorization, String refreshToken) {

        validarAuthorization(authorization);

        Optional<Auth> oldAuth = Auth.find("refreshToken", refreshToken).firstResultOptional();
        if (oldAuth.isEmpty() || !oldAuth.get().isFgValido() ||
                !oldAuth.get().getDtMovimento().plusHours(12).isAfter(LocalDateTime.now())) {
            throw new UnauthorizedException("Token expirado");
        }

        final Auth auth = new Auth(oldAuth.get().getUsuario());
        auth.persist();

        return new AuthDTO(auth.getAccessToken(), auth.getRefreshToken(), auth.getDtExpiracao());
    }

    public void revokeToken(String authorization) {

        Optional<Auth> auth = Auth.findByIdOptional(authorization);
        if (auth.isEmpty()) {
            throw new UnauthorizedException("Token invalido");
        }

        auth.get().setFgValido(false);
        auth.get().persist();
        SessionCache.removerSessao(auth.get().getAccessToken());
    }

    public void validarAuthorization(String authorization) {
        final String user = ConfigProvider.getConfig().getValue("oauth.basic.username", String.class);
        final String pass = ConfigProvider.getConfig().getValue("oauth.basic.password", String.class);

        if (!authorization.substring(6).equals(Base64.getEncoder().encodeToString((user + ":" + pass).getBytes()))) {
            throw new UnauthorizedException();
        }
    }

    public String decode(String param) {
        return URLDecoder.decode(param, StandardCharsets.UTF_8);
    }
}
