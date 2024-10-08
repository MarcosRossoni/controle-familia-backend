package com.endpoint;

import com.controller.security.RecuperarSenhaController;
import com.endpoint.auth.NoSession;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/recuperar-senha")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RecuperarSenhaEndpoint {

    @Inject
    RecuperarSenhaController recuperarSenhaController;

    @GET
    @NoSession
    public Response recuperarSenha(@QueryParam("email") String dsEmail) {
        recuperarSenhaController.recuperarSenha(dsEmail);
        return Response.ok().build();
    }

    @GET
    @Path("/verifica-token")
    @NoSession
    public Response verificaTokenResset(@QueryParam("token") String dsToken, @QueryParam("senha") String dsSenha) {
        recuperarSenhaController.verificarTokenRecuperacao(dsToken, dsSenha);
        return Response.ok().build();
    }
}
