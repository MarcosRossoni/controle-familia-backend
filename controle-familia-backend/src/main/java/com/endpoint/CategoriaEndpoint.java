package com.endpoint;

import com.controller.CategoriaController;
import com.dto.CategoriaDTO;
import com.dto.project.ListCategoriaProjectDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

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

    @GET
    @Path("buscar-categoria/{nome}/{fgTipo}")
    public Response autoCompleteCategoria(@PathParam("nome") String dsNome, @PathParam("fgTipo") Integer fgTipo){
        List<ListCategoriaProjectDTO> list = categoriaController.autoCompleteCategoria(dsNome, fgTipo);
        return Response.ok(list).build();
    }

}
