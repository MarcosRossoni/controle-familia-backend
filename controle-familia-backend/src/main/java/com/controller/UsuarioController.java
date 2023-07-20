package com.controller;

import com.controller.converter.UsuarioConverter;
import com.controller.security.HashingController;
import com.dto.UsuarioDTO;
import com.orm.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.UUID;

@ApplicationScoped
@Transactional
public class UsuarioController {

    @Inject
    UsuarioConverter usuarioConverter;

    public UsuarioDTO cadastrarUsuario(UsuarioDTO usuarioDTO){

        Usuario usuario = usuarioConverter.dtoToOrm(usuarioDTO);
        usuario.setDsSalt(UUID.randomUUID().toString());
        usuario.setDsSenha(HashingController.hashingSenha(
                usuarioDTO.getDsSenha(),
                usuario.getDsSalt()));
        usuario.setFgAtivo(true);
        usuario.persist();
        return usuarioConverter.ormToDto(usuario);
    }
}
