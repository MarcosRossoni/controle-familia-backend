package com.endpoint;

import com.controller.CategoriaController;
import com.dto.CategoriaDTO;
import com.dto.CategoriaFilterDTO;
import com.dto.PaginacaoDTO;
import com.dto.project.list.ListCategoriaProjectDTO;
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

    @PUT
    public Response updateCategoria(CategoriaDTO categoriaDTO){
        CategoriaDTO categoria = categoriaController.atualizarCategoria(categoriaDTO);
        return Response.ok(categoria).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        CategoriaDTO categoria = categoriaController.findById(id);
        return Response.ok(categoria).build();
    }

    @GET
    @Path("buscar-categoria/{nome}/{fgTipo}")
    public Response autoCompleteCategoria(@PathParam("nome") String dsNome, @PathParam("fgTipo") Integer fgTipo){
        List<ListCategoriaProjectDTO> list = categoriaController.autoCompleteCategoria(dsNome, fgTipo);
        return Response.ok(list).build();
    }

    @GET
    @Path("paginacao-categoria")
    public Response listPaginacao(CategoriaFilterDTO categoriaFilterDTO) {
        PaginacaoDTO paginacaoDTO = categoriaController.listCategoriaPaginacao(categoriaFilterDTO);
        return Response.ok(paginacaoDTO).build();
    }

}
