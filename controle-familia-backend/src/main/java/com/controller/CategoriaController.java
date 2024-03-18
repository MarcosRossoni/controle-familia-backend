package com.controller;

import com.controller.converter.CategoriaConverter;
import com.dto.CategoriaDTO;
import com.dto.project.list.ListCategoriaProjectDTO;
import com.enumeration.TipoMovimento;
import com.orm.Categoria;
import com.orm.Usuario;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

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

    public List<ListCategoriaProjectDTO> autoCompleteCategoria(String param, Integer fgTipo) {
        return Categoria.find("LOWER(dsDescricao) LIKE LOWER(?1) AND usuario.idUsuario = 1 AND fgTipo = ?2",
                        Sort.ascending("dsDescricao"), "%" + param + "%", TipoMovimento.values()[fgTipo])
                .project(ListCategoriaProjectDTO.class)
                .list();
    }
}
