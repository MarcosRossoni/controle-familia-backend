package com.controller.converter;

import com.dto.categoria.CategoriaDTO;
import com.enumeration.TipoMovimento;
import com.orm.Categoria;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CategoriaConverter extends GenericConverter<Categoria, CategoriaDTO>{

    @Inject
    UsuarioConverter usuarioConverter;

    @Override
    public CategoriaDTO ormToDto(Categoria categoria) {
        return ormToDto(categoria, new CategoriaDTO());
    }

    @Override
    public Categoria dtoToOrm(CategoriaDTO categoriaDTO) {
        return dtoToOrm(categoriaDTO, new Categoria());
    }

    @Override
    public CategoriaDTO ormToDto(Categoria categoria, CategoriaDTO categoriaDTO) {
        copy(categoria, categoriaDTO);
        categoriaDTO.setFgTipo(categoria.getFgTipo().ordinal());
        categoriaDTO.setUsuario(usuarioConverter.ormToDto(categoria.getUsuario()));
        return categoriaDTO;
    }

    @Override
    public Categoria dtoToOrm(CategoriaDTO categoriaDTO, Categoria categoria) {
        copy(categoriaDTO, categoria);
        categoria.setFgTipo(TipoMovimento.values()[categoriaDTO.getFgTipo()]);
        return categoria;
    }

    @Override
    protected String[] ignoreProperties() {
        return new String[]{"fgTipo", "usuario"};
    }
}
