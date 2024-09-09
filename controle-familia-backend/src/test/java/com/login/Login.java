package com.login;

import com.controller.security.AuthController;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Base64;

@ApplicationScoped
public class Login {

    private static final String USER_NAME = "admin@teste.com";
    private static final String PASSWORD = "admin";
    private static String token;

    @ConfigProperty(name = "AUTH_BASIC_USER")
    String basicUser;

    @ConfigProperty(name = "AUTH_BASIC_PASSWORD")
    String basicPassword;

    @Inject
    AuthController authController;

    public String getToken() {

        if (token == null) {
            final var authDTO = authController.generateToken(
                    generateAuthorization(),
                    USER_NAME,
                    PASSWORD
            );

            token = "Bearer " + authDTO.getAccessToken();
            return token;
        }
        return token;
    }

    private String generateAuthorization() {
        return "basic " + Base64.getEncoder().encodeToString((basicUser + ":" + basicPassword).getBytes());
    }
}
