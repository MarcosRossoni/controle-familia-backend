package com.controller;

import com.controller.converter.CategoriaConverter;
import com.dto.CategoriaDTO;
import com.orm.Categoria;
import com.orm.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class CategoriaController extends GenericController{

    @Inject
    CategoriaConverter categoriaConverter;

    public CategoriaDTO cadastroCategoria(CategoriaDTO categoriaDTO) {

        Categoria categoria = categoriaConverter.dtoToOrm(categoriaDTO);
        categoria.setUsuario(Usuario.findById(1));
        categoria.persist();

        return categoriaConverter.ormToDto(categoria);

    }
}
