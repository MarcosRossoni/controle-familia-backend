package com.dto.project.list;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public class ListUsuarioProjectDTO {

    private Long idUsuario;

    private String dsNome;

    private String dsEmail;

    private Boolean fgAtivo;

    public ListUsuarioProjectDTO(Long idUsuario, String dsNome, String dsEmail, Boolean fgAtivo) {
        this.idUsuario = idUsuario;
        this.dsNome = dsNome;
        this.dsEmail = dsEmail;
        this.fgAtivo = fgAtivo;
    }
}
