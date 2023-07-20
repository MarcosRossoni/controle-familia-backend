package com.controller.converter;

import com.dto.UsuarioDTO;
import com.orm.Usuario;

public class UsuarioConverter extends GenericConverter<Usuario, UsuarioDTO>{

    @Override
    public UsuarioDTO ormToDto(Usuario usuario) {
        return null;
    }

    @Override
    public Usuario dtoToOrm(UsuarioDTO usuarioDTO) {
        return null;
    }

    @Override
    public UsuarioDTO ormToDto(Usuario usuario, UsuarioDTO usuarioDTO) {
        return null;
    }

    @Override
    public Usuario dtoToOrm(UsuarioDTO usuarioDTO, Usuario usuario) {
        return null;
    }

    @Override
    protected String[] ignoreProperties() {
        return new String[0];
    }
}
