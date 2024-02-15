package com.endpoint;

import com.controller.CategoriaController;
import com.dto.CategoriaDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/categoria")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaEndpoint {

    @Inject
    CategoriaController categoriaController;

    @POST
    public Response createCategoria(CategoriaDTO categoriaDTO){
        CategoriaDTO categoria = categoriaController.cadastroCategoria(categoriaDTO);
        return Response.ok(categoria).build();
    }
}
