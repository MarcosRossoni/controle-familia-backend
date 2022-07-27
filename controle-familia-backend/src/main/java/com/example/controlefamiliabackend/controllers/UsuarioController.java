package com.example.controlefamiliabackend.controllers;

import com.example.controlefamiliabackend.dtos.UsuarioDto;
import com.example.controlefamiliabackend.forms.UsuarioForm;
import com.example.controlefamiliabackend.models.UsuarioModel;
import com.example.controlefamiliabackend.repositories.UsuarioRepository;
import com.example.controlefamiliabackend.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigInteger;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<UsuarioDto> saveUsuario(@RequestBody @Valid UsuarioForm usuarioForm, UriComponentsBuilder uriComponentsBuilder){
//        if(usuarioService.existsByDsEmail(usuarioDto.getDsEmail())){
//            //Mapper exception
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email ja cadastrado!");
//        }
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
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "id") BigInteger id){
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
        if(usuarioModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado!");
        }
        usuarioService.delete(usuarioModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> saveUsuario(@PathVariable(value = "id") BigInteger id,
                                               @RequestBody @Valid UsuarioDto usuarioDto){
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
        if(usuarioModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado!");
        }
        UsuarioModel usuarioModel = usuarioModelOptional.get();
        usuarioModel.setDsCpf(usuarioDto.getDsCpf());
        usuarioModel.setDsTelefone(usuarioDto.getDsTelefone());
        usuarioModel.setDtNascimento(usuarioDto.getDtNascimento());
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioModel));
    }
}
