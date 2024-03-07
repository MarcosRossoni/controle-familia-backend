package com.endpoint;

import com.controller.ContaBancariaController;
import com.dto.ContaBancariaDTO;
import com.dto.project.ListCidadesProjectDTO;
import com.dto.project.ListContasBancariasProjectDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/conta-bancaria")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContaBancarioEndpoint {

    @Inject
    ContaBancariaController contaBancariaController;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer idConta) {
        ContaBancariaDTO contaBancaria = contaBancariaController.findById(idConta);
        return Response.ok(contaBancaria).build();
    }

    @POST
    public Response cadastroContaBancaria(ContaBancariaDTO contaBancariaDTO){
        ContaBancariaDTO contaBancaria = contaBancariaController.cadastrarContaBancaria(contaBancariaDTO);
        return Response.ok(contaBancaria).build();
    }

    @PUT
    public Response alterarContaBancaria(ContaBancariaDTO contaBancariaDTO){
        ContaBancariaDTO contaBancaria = contaBancariaController.atualizarContaBancaria(contaBancariaDTO);
        return Response.ok(contaBancaria).build();
    }

    @GET
    public Response listarContas () {
        List<ListContasBancariasProjectDTO> list = contaBancariaController.findAll();
        return Response.ok(list).build();
    }

    @GET
    @Path("buscar-conta/{nome}")
    public Response buscarCidade(@PathParam("nome") String dsNome){
        List<ListContasBancariasProjectDTO> list = contaBancariaController.autoCompleteContaBancaria(dsNome);
        return Response.ok(list).build();
    }
}
