package com.endpoint;

import com.controller.ContaBancariaController;
import com.dto.ContaBancariaDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/conta-bancaria")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContaBancarioEndpoint {

    @Inject
    ContaBancariaController contaBancariaController;

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
}
