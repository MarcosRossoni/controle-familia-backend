package com.endpoint.auth;

import com.controller.SessionHolder;
import com.controller.cache.SessionCache;
import com.dao.AuthDAO;
import com.dto.exception.ResponseErroDTO;
import com.orm.Auth;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Locale;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

    @Context
    ResourceInfo info;

    @Inject
    AuthDAO authDAO;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        Locale.setDefault(new Locale("pt", "BR"));

        if (info.getResourceMethod().isAnnotationPresent(NoSession.class)) {
            return;
        }

        if (validarAcessToken(requestContext)) {
            return;
        }

        abortRequest(requestContext);
    }

    //--

    private boolean validarAcessToken(ContainerRequestContext requestContext) {
        String header = requestContext.getHeaderString("Authorization");
        if (header == null || header.trim().isEmpty() || !header.startsWith("Bearer ")) {
            return false;
        }

        header = header.substring(7);

        Auth auth = SessionCache.carregarSessao(header);
        if (auth == null || !auth.isFgValido() || auth.getDtExpiracao().isBefore(LocalDateTime.now())){
            auth = Auth.findById(header);
            if (auth == null || !auth.isFgValido() || auth.getDtExpiracao().isBefore(LocalDateTime.now())) {
                return false;
            }
        }

        authDAO.detach(auth);
        SessionHolder.setUserToken(header);
        SessionCache.gravarSessao(auth);
        return true;
    }

    private void abortRequest(ContainerRequestContext requestContext) {

        final ResponseErroDTO error = new ResponseErroDTO(
                Response.Status.UNAUTHORIZED,
                "Usuario não Autorizado");

        requestContext.abortWith(Response
                .status(Response.Status.UNAUTHORIZED)
                .entity(error)
                .build()
        );
    }
}
