package com.dto.project.list;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public class ListCidadesProjectDTO {

    private Long idCidade;

    private String dsNome;

    private String dsSiglaEstado;

    private String dsNomeEstado;

    private String dsRegiao;

    public ListCidadesProjectDTO(Long idCidade, String dsNome, String dsSiglaEstado, String dsNomeEstado, String dsRegiao) {
        this.idCidade = idCidade;
        this.dsNome = dsNome;
        this.dsSiglaEstado = dsSiglaEstado;
        this.dsNomeEstado = dsNomeEstado;
        this.dsRegiao = dsRegiao;
    }
}
