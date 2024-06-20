package com.controller.email;

import com.dto.brevo.BrevoEmailDTO;
import com.dto.brevo.BrevoEmailDestinatarioDTO;
import com.dto.brevo.BrevoEmailRementeDTO;
import com.restclient.EnviarEmailRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class EnviaEmailController {

    @Inject
    @RestClient
    EnviarEmailRestClient enviarEmailRestClient;

    @ConfigProperty(name = "BREVO_API_KEY")
    protected String BREVO_API_KEY;

    public void enviaEmail(String dsRefactoryKey, String dsEmailDestinatario) {

        BrevoEmailDTO brevoEmailDTO = new BrevoEmailDTO();
        BrevoEmailDestinatarioDTO brevoEmailDestinatarioDTO = new BrevoEmailDestinatarioDTO();
        BrevoEmailRementeDTO brevoEmailRementeDTO = new BrevoEmailRementeDTO();

        brevoEmailDTO.setDsHtmlCorpoEmail(
                "<!DOCTYPE html> <html> <body> <h1>Use o código para redefinir sua senha "+ dsRefactoryKey + "</h1> </html>");
        brevoEmailDTO.setDsTitulo("Teste testudo");

        brevoEmailRementeDTO.setDsNomeEmail("Marcos");
        brevoEmailRementeDTO.setDsRemetenteEmail("no-reply@myshop.com");
        brevoEmailDTO.setBrevoEmailRemente(brevoEmailRementeDTO);

        brevoEmailDestinatarioDTO.setDsNomeDestinatario("Marcos");
        brevoEmailDestinatarioDTO.setEmailDestinatario(dsEmailDestinatario);
        brevoEmailDTO.getDestinatarios().add(brevoEmailDestinatarioDTO);

        JsonObject jsonObject = enviarEmailRestClient.enviarEmail(BREVO_API_KEY, brevoEmailDTO);
        System.out.println(jsonObject);

    }
}
