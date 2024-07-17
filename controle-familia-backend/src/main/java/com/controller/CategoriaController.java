package com.controller;

import com.controller.converter.CategoriaConverter;
import com.controller.session.Session;
import com.controller.session.SessionModel;
import com.dto.CategoriaDTO;
import com.dto.CategoriaFilterDTO;
import com.dto.PaginacaoDTO;
import com.dto.project.list.ListCategoriaProjectDTO;
import com.enumeration.TipoMovimento;
import com.orm.Categoria;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
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

    @Session
    SessionModel userSession;

    public CategoriaDTO cadastroCategoria(CategoriaDTO categoriaDTO) {

        Categoria categoria = categoriaConverter.dtoToOrm(categoriaDTO);
        categoria.setUsuario(userSession.getUsuario());
        categoria.persist();

        return categoriaConverter.ormToDto(categoria);

    }

    public CategoriaDTO findById(Long id) {
        Categoria categoria = Categoria.findById(id);
        if (categoria == null) {
            return null;
        }
        return categoriaConverter.ormToDto(categoria);
    }

    public CategoriaDTO atualizarCategoria(CategoriaDTO categoriaDTO) {

        Categoria categoria = Categoria.findById(categoriaDTO.getIdCategoria());
        if (categoria == null) {
            return null;
        }

        categoriaConverter.dtoToOrm(categoriaDTO, categoria);
        categoria.persist();
        return categoriaConverter.ormToDto(categoria);

    }

    public List<ListCategoriaProjectDTO> autoCompleteCategoria(String param, Integer fgTipo) {
        return Categoria.find("LOWER(dsDescricao) LIKE LOWER(?1) AND usuario.idUsuario = 1 AND fgTipo = ?2",
                        Sort.ascending("dsDescricao"), "%" + param + "%", TipoMovimento.values()[fgTipo])
                .project(ListCategoriaProjectDTO.class)
                .list();
    }

    public PaginacaoDTO listCategoriaPaginacao(CategoriaFilterDTO categoriaFilterDTO) {
        PanacheQuery<ListCategoriaProjectDTO> listPaginacao = Categoria.find("usuario.idUsuario = ?1",
                        Sort.by("idCategoria"),
                        userSession.getUsuario().getIdUsuario())
                .project(ListCategoriaProjectDTO.class);

        return new PaginacaoDTO(listPaginacao, categoriaFilterDTO);
    }
}
