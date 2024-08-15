package com.dto.brevo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BrevoEmailRementeDTO {

    @JsonProperty("name")
    private String dsNomeEmail;

    @JsonProperty("email")
    private String dsRemetenteEmail;
}
