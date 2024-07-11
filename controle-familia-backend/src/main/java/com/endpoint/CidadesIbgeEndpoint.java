package com.endpoint;

import com.controller.CidadesController;
import com.dto.project.list.ListCidadesProjectDTO;
import com.endpoint.auth.NoSession;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/cidades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CidadesIbgeEndpoint {

    @Inject
    CidadesController cidadesController;

    @GET
    public Response cadastrarCidades(){
        cidadesController.cadastroCidade();
        return Response.ok().build();
    }

    @GET
    @Path("buscar-cidade/{nome}")
    @NoSession
    public Response buscarCidade(@PathParam("nome") String dsNome){
        List<ListCidadesProjectDTO> listCidades = cidadesController.autoCompleteCidade(dsNome);
        return Response.ok(listCidades).build();
    }
}
