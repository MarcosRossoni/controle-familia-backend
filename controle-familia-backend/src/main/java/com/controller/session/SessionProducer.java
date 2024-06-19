package com.controller.session;

import com.controller.cache.SessionCache;
import com.dao.AuthDAO;
import com.orm.Auth;
import io.quarkus.security.UnauthorizedException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class SessionProducer {

    @Inject
    AuthDAO authDAO;

    @Default
    @Session
    @RequestScoped
    public SessionModel loadSession() {
        return carregarSession();
    }

    //--

    private SessionModel carregarSession() {
        final String token = SessionHolder.getUserToken();
        if (token == null) {
            throw new UnauthorizedException();
        }

        Auth auth = SessionCache.carregarSessao(token);
        if (auth == null) {
            auth = Auth.find("accessToken", token).firstResult();
            if (auth == null) {
                throw new UnauthorizedException();
            }

            authDAO.detach(auth);
            SessionCache.gravarSessao(auth);
        }

        return new SessionModel(auth.getUsuario());
    }
}
