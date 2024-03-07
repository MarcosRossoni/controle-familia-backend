package com.dto.project;

import com.enumeration.TipoMovimento;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public class ListCategoriaProjectDTO {

    private Long idCategoria;

    private String dsDescricao;

    private String dsCor;

    private Integer fgTipo;

    public ListCategoriaProjectDTO(Long idCategoria, String dsDescricao, String dsCor, TipoMovimento fgTipo) {
        this.idCategoria = idCategoria;
        this.dsDescricao = dsDescricao;
        this.dsCor = dsCor;
        this.fgTipo = fgTipo.ordinal();
    }
}
