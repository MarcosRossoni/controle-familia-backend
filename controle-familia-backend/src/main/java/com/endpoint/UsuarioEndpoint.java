package com.endpoint;

import com.controller.UsuarioController;
import com.dto.usuario.TrocarSenhaUsuarioDTO;
import com.dto.usuario.UsuarioAtualizacaoDTO;
import com.dto.usuario.UsuarioDTO;
import com.dto.usuario.project.ListUsuarioProjectDTO;
import com.endpoint.auth.NoSession;
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
    @NoSession
    public Response cadastrarUsuario(UsuarioDTO usuarioDTO){
        UsuarioDTO usuario = usuarioController.cadastrarUsuario(usuarioDTO);
        return Response.ok(usuario).build();
    }

    @PUT
    public Response atualizacaoUsuario(UsuarioAtualizacaoDTO usuarioAtualizacaoDTO){
        UsuarioDTO usuario = usuarioController.alteracaoUsuario(usuarioAtualizacaoDTO);
        return Response.ok(usuario).build();
    }

    @PATCH
    @Path("atualizar-senha")
    public Response atualizarSenha(TrocarSenhaUsuarioDTO usuarioDTO){
        usuarioController.alterarSenha(usuarioDTO);
        return Response.ok().build();
    }

    @GET
    public Response listUsuarios(){
        List<ListUsuarioProjectDTO> listUsuarios = usuarioController.listUsuarios();
        return Response.ok(listUsuarios).build();
    }
}
