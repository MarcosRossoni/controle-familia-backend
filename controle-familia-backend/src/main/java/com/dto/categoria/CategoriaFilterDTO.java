package com.dto.categoria;

import com.dto.paginacao.PaginacaoDTO;
import jakarta.ws.rs.QueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class CategoriaFilterDTO extends PaginacaoDTO {

    @QueryParam("dsNomeCategoria")
    private String dsNomeCategoria;

}
