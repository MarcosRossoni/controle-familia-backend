package com.example.controlefamiliabackend.controllers;

import com.example.controlefamiliabackend.dtos.UsuarioDto;
import com.example.controlefamiliabackend.forms.AtulizacaoUsuarioForm;
import com.example.controlefamiliabackend.forms.UsuarioForm;
import com.example.controlefamiliabackend.models.UsuarioModel;
import com.example.controlefamiliabackend.repositories.UsuarioRepository;
import com.example.controlefamiliabackend.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigInteger;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {

    final UsuarioService usuarioService;

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> saveUsuario(@RequestBody @Valid UsuarioForm usuarioForm,
                                                  UriComponentsBuilder uriComponentsBuilder){
        UsuarioModel usuarioModel = usuarioForm.converter();
        URI uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuarioModel.getId()).toUri();
        usuarioRepository.save(usuarioModel);
        return ResponseEntity.created(uri).body(new UsuarioDto(usuarioModel));
    }

    @GetMapping
    public List<UsuarioDto> getAllUsuario(){
        List<UsuarioModel> usuario = usuarioRepository.findAll();
        return UsuarioDto.converterList(usuario);
    }

    @GetMapping("/{id}")
    public UsuarioDto getUsuario(@PathVariable(value = "id") BigInteger id){
        UsuarioModel usuario = usuarioRepository.getReferenceById(id);
        return UsuarioDto.converter(usuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") BigInteger id){
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioDto> atulizarUsuario(@PathVariable(value = "id") BigInteger id,
                                                      @RequestBody @Valid AtulizacaoUsuarioForm atualizarUsuarioForm){
        UsuarioModel usuarioModel = atualizarUsuarioForm.atualizar(id, usuarioRepository);
        return ResponseEntity.ok(new UsuarioDto(usuarioModel));
    }
}
