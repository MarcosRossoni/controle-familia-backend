package com.endpoint;

import com.controller.MovimentoController;
import com.dto.movimento.MovimentoAtualizarCadastrarDTO;
import com.dto.movimento.MovimentoDTO;
import com.dto.paginacao.PaginacaoDTO;
import com.dto.movimento.MovimentoFilterDTO;
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
    public Response cadastroMovimento(MovimentoAtualizarCadastrarDTO movimentoDTO){
        MovimentoDTO movimento = movimentoController.cadastroMovimento(movimentoDTO);
        return Response.ok(movimento).build();
    }

    @PUT
    public Response alterarMovimento(MovimentoAtualizarCadastrarDTO movimentoDTO){
        MovimentoDTO movimento = movimentoController.alterarMovimento(movimentoDTO);
        return Response.ok(movimento).build();
    }

    @DELETE
    @Path("/{idMovimento}")
    public Response excluirMovimento(@PathParam("idMovimento") Long idMovimento){
        movimentoController.deletMovimento(idMovimento);
        return Response.ok().build();
    }

    @GET
    @Path("/{idMovimento}/{nrParcela}")
    public Response findById(@PathParam("idMovimento") Long idMovimento, @PathParam("nrParcela") Long nrParcela) {
        MovimentoDTO movimentoDTO = movimentoController.findByIdMovimento(idMovimento, nrParcela);
        return Response.ok(movimentoDTO).build();
    }

    @GET
    @Path("/list-movimento")
    public Response listarMovimentos(MovimentoFilterDTO movimentoFilterDTO){
        PaginacaoDTO listMovimento = movimentoController.listarMovimentos(movimentoFilterDTO);
        return Response.ok(listMovimento).build();
    }

}
