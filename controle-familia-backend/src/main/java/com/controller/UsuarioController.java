package com.controller;

import com.controller.converter.UsuarioConverter;
import com.controller.security.HashingController;
import com.dto.UsuarioDTO;
import com.dto.project.list.ListUsuarioProjectDTO;
import com.enumeration.LogEnum;
import com.orm.Cidade;
import com.orm.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class UsuarioController extends GenericController{

    @Inject
    UsuarioConverter usuarioConverter;

    public UsuarioDTO cadastrarUsuario(UsuarioDTO usuarioDTO){

        if (Usuario.find("dsEmail", usuarioDTO.getDsEmail()).firstResult() != null){
            throw new BadRequestException("usuario.existe");
        }

        Usuario usuario = usuarioConverter.dtoToOrm(usuarioDTO);
        usuario.setDsSalt(UUID.randomUUID().toString());
        usuario.setDsSenha(HashingController.hashingSenha(
                usuarioDTO.getDsSenha(),
                usuario.getDsSalt()));
        usuario.setFgAtivo(true);
        usuario.setDtCadastro(LocalDateTime.now());
        usuario.setDtAlteracao(LocalDateTime.now());
        usuario.setCidade(Cidade.findById(usuarioDTO.getCidade().getIdCidade()));
        usuario.persist();
        return usuarioConverter.ormToDto(usuario);
    }

    public UsuarioDTO alteracaoUsuario(UsuarioDTO usuarioDTO){

        Usuario usuario = Usuario.findById(usuarioDTO.getIdUsuario());

        usuarioConverter.dtoToOrm(usuarioDTO, usuario);

        usuario.setDtAlteracao(LocalDateTime.now());
        usuario.setCidade(Cidade.findById(usuarioDTO.getCidade().getIdCidade()));
        usuario.persist();

        registrarLog(usuario, "Alteracao de dados de usuario", LogEnum.USUARIO);

        return usuarioConverter.ormToDto(usuario);
    }

    public List<ListUsuarioProjectDTO> listUsuarios() {
        return Usuario.findAll().project(ListUsuarioProjectDTO.class).list();
    }
}
