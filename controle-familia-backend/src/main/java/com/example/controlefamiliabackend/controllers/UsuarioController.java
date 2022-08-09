package com.example.controlefamiliabackend.controllers;

import com.example.controlefamiliabackend.dtos.UsuarioDto;
import com.example.controlefamiliabackend.forms.AtulizacaoUsuarioForm;
import com.example.controlefamiliabackend.forms.UsuarioForm;
import com.example.controlefamiliabackend.models.UsuarioModel;
import com.example.controlefamiliabackend.repositories.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigInteger;
import java.net.URI;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
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
    public Page<UsuarioDto> getAllUsuario(@PageableDefault(sort = "id",
            direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao){
        Page<UsuarioModel> usuario = usuarioRepository.findAll(paginacao);
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
