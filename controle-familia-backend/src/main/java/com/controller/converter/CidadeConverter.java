package com.controller.converter;

import com.dto.cidade.CidadeDTO;
import com.orm.Cidade;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CidadeConverter extends GenericConverter<Cidade, CidadeDTO>{
    @Override
    public CidadeDTO ormToDto(Cidade cidade) {
        return ormToDto(cidade, new CidadeDTO());
    }

    @Override
    public Cidade dtoToOrm(CidadeDTO cidadeDTO) {
        return dtoToOrm(cidadeDTO, new Cidade());
    }

    @Override
    public CidadeDTO ormToDto(Cidade cidade, CidadeDTO cidadeDTO) {
        copy(cidade, cidadeDTO);
        return cidadeDTO;
    }

    @Override
    public Cidade dtoToOrm(CidadeDTO cidadeDTO, Cidade cidade) {
        copy(cidadeDTO, cidade);
        return cidade;
    }

    @Override
    protected String[] ignoreProperties() {
        return new String[0];
    }
}
