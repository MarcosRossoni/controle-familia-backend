package com.endpoint;

import com.controller.UsuarioController;
import com.dto.UsuarioDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioEndpoint {

    @Inject
    UsuarioController usuarioController;

    @POST
    public Response cadastrarUsuario(UsuarioDTO usuarioDTO){
        UsuarioDTO usuario = usuarioController.cadastrarUsuario(usuarioDTO);
        return Response.ok(usuario).build();
    }
}
