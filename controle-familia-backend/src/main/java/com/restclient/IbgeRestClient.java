package com.restclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonArray;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "restclient-ibge-api")
@Produces(MediaType.APPLICATION_JSON)
@RegisterProvider(ObjectMapper.class)
public interface IbgeRestClient {

    @GET
    @Path("/localidades/municipios")
    JsonArray buscarCidades ();
}
