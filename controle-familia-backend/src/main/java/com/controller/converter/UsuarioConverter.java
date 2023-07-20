package com.controller.converter;

import com.dto.UsuarioDTO;
import com.orm.Usuario;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioConverter extends GenericConverter<Usuario, UsuarioDTO>{

    @Override
    public UsuarioDTO ormToDto(Usuario usuario) {
        return ormToDto(usuario, new UsuarioDTO());
    }

    @Override
    public Usuario dtoToOrm(UsuarioDTO usuarioDTO) {
        return dtoToOrm(usuarioDTO, new Usuario());
    }

    @Override
    public UsuarioDTO ormToDto(Usuario usuario, UsuarioDTO usuarioDTO) {

        copy(usuario, usuarioDTO);

        return usuarioDTO;
    }

    @Override
    public Usuario dtoToOrm(UsuarioDTO usuarioDTO, Usuario usuario) {

        copy(usuarioDTO, usuario);

        return usuario;
    }

    @Override
    protected String[] ignoreProperties() {
        return new String[]{"dsSenha", "dsSalt"};
    }
}
