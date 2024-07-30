package com.endpoint;

import com.controller.MovimentoController;
import com.dto.MovimentoAtualizarCadastrarDTO;
import com.dto.MovimentoDTO;
import com.dto.PaginacaoDTO;
import com.dto.filter.MovimentoFilterDTO;
import com.dto.project.list.ListMovimentoProjectDTO;
import com.dto.project.projectdto.MovimentoProjectDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/movimento")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovimentoEndpoint {

    @Inject
    MovimentoController movimentoController;

    @POST
    public Response cadastroMovimento(MovimentoAtualizarCadastrarDTO movimentoDTO){
        MovimentoDTO movimento = movimentoController.cadastroMovimento(movimentoDTO);
        return Response.ok(movimento).build();
    }

    @PUT
    public Response alterarMovimento(MovimentoAtualizarCadastrarDTO movimentoDTO){
        MovimentoDTO movimento = movimentoController.alterarMovimento(movimentoDTO);
        return Response.ok(movimento).build();
    }

    @GET
    @Path("/{idMovimento}")
    public Response findById(@PathParam("idMovimento") Long idMovimento) {
        MovimentoDTO movimentoDTO = movimentoController.findByIdMovimento(idMovimento);
        return Response.ok(movimentoDTO).build();
    }

    @GET
    @Path("/list-movimento")
    public Response listarMovimentos(MovimentoFilterDTO movimentoFilterDTO){
        PaginacaoDTO listMovimento = movimentoController.listarMovimentos(movimentoFilterDTO);
        return Response.ok(listMovimento).build();
    }

}
