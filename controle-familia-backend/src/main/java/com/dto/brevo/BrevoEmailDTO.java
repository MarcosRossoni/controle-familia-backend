package com.dto.brevo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BrevoEmailDTO {

    @JsonProperty("sender")
    private BrevoEmailRementeDTO brevoEmailRemente;

    @JsonProperty("htmlContent")
    private String dsHtmlCorpoEmail;

    @JsonProperty("subject")
    private String dsTitulo;

    @JsonProperty("to")
    private List<BrevoEmailDestinatarioDTO> destinatarios = new ArrayList<>();
}
