package com.restclient;

import com.dto.brevo.BrevoEmailDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "restclient-brevo-api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterProvider(ObjectMapper.class)
public interface EnviarEmailRestClient {

    @POST
    @Path("/smtp/email")
    JsonObject enviarEmail(@HeaderParam("api-key") String apiKey,
                           BrevoEmailDTO brevoEmailDTO);
}
