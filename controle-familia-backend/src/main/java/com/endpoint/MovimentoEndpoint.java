package com.endpoint;

import com.controller.MovimentoController;
import com.dto.MovimentoDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/movimento")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovimentoEndpoint {

    @Inject
    MovimentoController movimentoController;

    @POST
    public Response cadastroMovimento(MovimentoDTO movimentoDTO){
        MovimentoDTO movimento = movimentoController.cadastroMovimento(movimentoDTO);
        return Response.ok(movimento).build();
    }

    @PUT
    public Response alterarMovimento(MovimentoDTO movimentoDTO){
        MovimentoDTO movimento = movimentoController.alterarMovimento(movimentoDTO);
        return Response.ok(movimento).build();
    }

}
