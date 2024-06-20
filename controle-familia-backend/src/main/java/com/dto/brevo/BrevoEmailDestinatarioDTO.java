package com.dto.brevo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BrevoEmailDestinatarioDTO {

    @JsonProperty("email")
    private String emailDestinatario;

    @JsonProperty("name")
    private String dsNomeDestinatario;
}
