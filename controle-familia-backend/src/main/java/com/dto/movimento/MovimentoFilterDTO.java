package com.dto.movimento;

import com.dto.paginacao.PaginacaoDTO;
import jakarta.ws.rs.QueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MovimentoFilterDTO extends PaginacaoDTO {

    @QueryParam("fgTipoMovimento")
    private Integer fgTipoMovimento;

    @QueryParam("idCategoria")
    private Integer idCategoria;

}
