package com.example.controlefamiliabackend.config.validacao.security;

import com.example.controlefamiliabackend.models.UsuarioModel;
import com.example.controlefamiliabackend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepository repository;

    public AutenticacaoService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String dsEmail) throws UsernameNotFoundException {
        Optional<UsuarioModel> usuarioModel = repository.findByDsEmail(dsEmail);
        if (usuarioModel.isPresent()){
            return usuarioModel.get();
        }
        throw new UsernameNotFoundException("Dados invalidos!");
    }
}
