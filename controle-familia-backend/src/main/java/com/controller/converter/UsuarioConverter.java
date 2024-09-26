package com.controller.converter;

import com.dto.usuario.UsuarioDTO;
import com.orm.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;

@ApplicationScoped
public class UsuarioConverter extends GenericConverter<Usuario, UsuarioDTO>{

    @Inject
    CidadeConverter cidadeConverter;

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
        usuarioDTO.setCidade(cidadeConverter.ormToDto(usuario.getCidade()));
        usuarioDTO.setDtCadastro(usuario.getDtCadastro().toString());
        usuarioDTO.setDtAlteracao(usuario.getDtAlteracao().toString());
        return usuarioDTO;
    }

    @Override
    public Usuario dtoToOrm(UsuarioDTO usuarioDTO, Usuario usuario) {

        copy(usuarioDTO, usuario);
        usuario.setDtNascimento(LocalDate.parse(usuarioDTO.getDtNascimento()));
        return usuario;
    }

    @Override
    protected String[] ignoreProperties() {
        return new String[]{"dsSenha", "dsSalt", "dtCadastro", "dtAlteracao"};
    }
}
