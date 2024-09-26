package com.dto.paginacao;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import jakarta.ws.rs.QueryParam;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaginacaoDTO {

    @QueryParam("currentPage")
    private int currentPage;

    @QueryParam("pageSize")
    private int pageSize;

    @QueryParam("totalItens")
    private int totalItens;

    @QueryParam("dtInicio")
    private String dtInicio;

    @QueryParam("dtFim")
    private String dtFim;

    private Iterable<?> data;

    public PaginacaoDTO(PanacheQuery<?> result, PaginacaoDTO paginacaoDTO) {

        result.page(Page.of(paginacaoDTO.getCurrentPage(), paginacaoDTO.getPageSize()));
        if (result.pageCount() == 0){
            result.page(Page.of(0, paginacaoDTO.getPageSize()));
        }

        this.currentPage = result.page().index + 1;
        this.pageSize = result.page().size;
        this.totalItens = (int) result.count();
        this.data = result.list();
    }
}
