package com.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
public class AuthDTO {

    @JsonProperty("acces_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private Long expiresIn;

    public AuthDTO() {
    }

    public AuthDTO(String accessToken, String refreshToken, LocalDateTime dtExpeiracao) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = "bearer";
        this.expiresIn = ChronoUnit.SECONDS.between(LocalDateTime.now(), dtExpeiracao);
    }
}
