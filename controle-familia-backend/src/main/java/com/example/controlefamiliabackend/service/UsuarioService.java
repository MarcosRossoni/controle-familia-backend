package com.example.controlefamiliabackend.service;

import com.example.controlefamiliabackend.forms.UsuarioForm;
import com.example.controlefamiliabackend.models.UsuarioModel;
import com.example.controlefamiliabackend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    final PasswordEncoder passwordEncoder;
    final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    public String hashSenha(String senha){
        return passwordEncoder.encode(senha);
    }

    public UsuarioModel registroDeUsuario(UsuarioForm usuarioForm){

        UsuarioModel usuarioModel = new UsuarioModel();

        usuarioModel.setDsEmail(usuarioForm.getDsEmail());
        usuarioModel.setDsSenha(passwordEncoder.encode(usuarioForm.getDsSenha()));
        usuarioModel.setDsNome(usuarioForm.getDsNome());
        usuarioModel.setDsTelefone(usuarioForm.getDsTelefone());
        usuarioModel.setDsCpf(usuarioForm.getDsCpf());
        usuarioModel.setDtNascimento(usuarioForm.getDtNascimento());
        usuarioModel.setDsEndereco(usuarioForm.getDsEndereco());

        return usuarioRepository.save(usuarioModel);
    }
}
