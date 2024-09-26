package com.controller;

import com.controller.converter.UsuarioConverter;
import com.controller.security.HashingController;
import com.controller.session.Session;
import com.controller.session.SessionModel;
import com.dto.usuario.TrocarSenhaUsuarioDTO;
import com.dto.usuario.UsuarioAtualizacaoDTO;
import com.dto.usuario.UsuarioDTO;
import com.dto.usuario.project.ListUsuarioProjectDTO;
import com.enumeration.LogEnum;
import com.orm.Cidade;
import com.orm.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class UsuarioController extends GenericController {

    @Inject
    UsuarioConverter usuarioConverter;

    @Session
    SessionModel userSession;

    public UsuarioDTO cadastrarUsuario(UsuarioDTO usuarioDTO) {

        if (Usuario.find("dsEmail", usuarioDTO.getDsEmail()).firstResult() != null) {
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

    public UsuarioDTO alteracaoUsuario(UsuarioAtualizacaoDTO usuarioDTO) {

        Usuario usuario = Usuario.findById(usuarioDTO.idUsuario());

        usuario.setDsNome(usuarioDTO.dsNome());
        usuario.setDsTelefone(usuarioDTO.dsTelefone());
        usuario.setDtNascimento(LocalDate.parse(usuarioDTO.dtNascimento()));
        usuario.setDsCpf(usuarioDTO.dsCpf());
        usuario.setDsEndereco(usuarioDTO.dsEndereco());
        usuario.setNumPredial(usuarioDTO.numPredial());
        usuario.setDsBairro(usuarioDTO.dsBairro());
        usuario.setDsComplemento(usuarioDTO.dsComplemento());
        usuario.setCidade(Cidade.findById(usuarioDTO.cidade().getIdCidade()));
        usuario.setDtAlteracao(LocalDateTime.now());
        usuario.persist();

        registrarLog(userSession.getUsuario(), "Alteracao de dados de usuario", LogEnum.USUARIO);

        return usuarioConverter.ormToDto(usuario);
    }

    public void alterarSenha(TrocarSenhaUsuarioDTO trocarSenhaUsuarioDTO){
        Usuario usuario = Usuario.findById(userSession.getUsuario().getIdUsuario());

        String hashingSenha = HashingController.hashingSenha(trocarSenhaUsuarioDTO.dsSenhaAntiga(), usuario.getDsSalt());

        if (!hashingSenha.equals(usuario.getDsSenha())){
            throw new BadRequestException("Senha não correspondente para alteração!");
        }

        String dsNewSalt = UUID.randomUUID().toString();
        usuario.setDsSenha(HashingController.hashingSenha(trocarSenhaUsuarioDTO.dsSenhaNova(), dsNewSalt));
        usuario.setDsSalt(dsNewSalt);
        usuario.persist();
    }

    public List<ListUsuarioProjectDTO> listUsuarios() {
        return Usuario.findAll().project(ListUsuarioProjectDTO.class).list();
    }
}
