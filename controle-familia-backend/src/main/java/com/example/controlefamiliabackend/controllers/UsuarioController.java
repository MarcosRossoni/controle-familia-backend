package com.example.controlefamiliabackend.controllers;

import com.example.controlefamiliabackend.dtos.UsuarioDto;
import com.example.controlefamiliabackend.models.UsuarioModel;
import com.example.controlefamiliabackend.services.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {

    final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @ResponseBody
    @PostMapping
    public ResponseEntity<Object> saveUsuario(@RequestBody @Valid UsuarioDto usuarioDto){
        if(usuarioService.existsByDsEmail(usuarioDto.getDsEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email ja cadastrado!");
        }
        UsuarioModel usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, usuarioModel);
        usuarioModel.setDtCadastro(LocalDateTime.now(ZoneId.of("UTC-3")));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioModel));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> getAllUsuario(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsuario(@PathVariable(value = "id") Integer id){
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
        return usuarioModelOptional.<ResponseEntity<Object>>map(
                usuarioModel -> ResponseEntity.status(HttpStatus.OK).body(usuarioModel))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "id") Integer id){
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
        if(usuarioModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado!");
        }
        usuarioService.delete(usuarioModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> saveUsuario(@PathVariable(value = "id") Integer id,
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
