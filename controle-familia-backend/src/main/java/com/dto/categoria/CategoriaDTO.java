package com.dto.categoria;

import com.dto.usuario.UsuarioDTO;
import lombok.Data;

@Data
public class CategoriaDTO {

    private Long idCategoria;

    private String dsDescricao;

    private String dsCor;

    private Integer fgTipo;

    private UsuarioDTO usuario;
}
