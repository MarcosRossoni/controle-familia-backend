package com.endpoint;

import com.controller.UsuarioController;
import com.dto.UsuarioDTO;
import com.dto.project.ListUsuarioProjectDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

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

    @PUT
    public Response atualizacaoUsuario(UsuarioDTO usuarioDTO){
        UsuarioDTO usuario = usuarioController.alteracaoUsuario(usuarioDTO);
        return Response.ok(usuario).build();
    }

    @GET
    public Response listUsuarios(){
        List<ListUsuarioProjectDTO> listUsuarios = usuarioController.listUsuarios();
        return Response.ok(listUsuarios).build();
    }
}
