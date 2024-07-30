package com.dto.filter;

import com.dto.PaginacaoDTO;
import jakarta.ws.rs.QueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class CategoriaFilterDTO extends PaginacaoDTO {

    @QueryParam("dsNomeCategoria")
    private String dsNomeCategoria;

}
