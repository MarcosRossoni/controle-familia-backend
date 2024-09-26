package com.endpoint;

import com.controller.security.AuthController;
import com.dto.auth.AuthDTO;
import com.endpoint.auth.NoSession;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/oauth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthEndpoint {

    @Inject
    AuthController authController;

    @GET
    @NoSession
    @Path("/token")
    public Response gerarAcesso(@HeaderParam("Authorization") String authorization,
                                @QueryParam("refresh_token") String refreshToken,
                                @QueryParam("grant_type") String grantType,
                                @QueryParam("username") String username,
                                @QueryParam("password") String password){

        if (authorization == null || !authorization.toLowerCase().startsWith("basic ")) {
            throw new BadRequestException("Token invalido");
        }
        if (grantType == null || (!grantType.equals("password") && !grantType.equals("refresh_token"))) {
            throw new BadRequestException("Token invalido");
        }

        final AuthDTO auth = grantType.equals("password") ?
                authController.generateToken(authorization, username, password) :
                authController.refreshToken(authorization, refreshToken);

        return Response.ok(auth).build();
    }

    @PATCH
    @Path("/revoke")
    public Response revokeToken(@HeaderParam("Authorization") String accesToken){
        authController.revokeToken(accesToken.substring(7));
        return Response.ok(Response.Status.ACCEPTED).build();
    }
}
