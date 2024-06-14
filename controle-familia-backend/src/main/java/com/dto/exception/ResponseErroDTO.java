package com.dto.exception;

import jakarta.ws.rs.core.Response;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseErroDTO {

    private final int code;

    private final String status;

    private final String message;

    private final String timestamp = LocalDateTime.now().toString();

    public ResponseErroDTO(Response.Status status, String message) {
        this.code = status.getStatusCode();
        this.status = status.getReasonPhrase();
        this.message = message;
    }
}
