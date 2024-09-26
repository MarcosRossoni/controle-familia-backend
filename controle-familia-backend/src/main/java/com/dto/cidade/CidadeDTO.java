package com.dto.cidade;

import lombok.Data;

@Data
public class CidadeDTO {

    private Long idCidade;

    private String dsNome;

    private String dsSiglaEstado;

    private String dsNomeEstado;

    private String dsRegiao;
}
