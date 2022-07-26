package com.example.controlefamiliabackend.controllers;


import com.example.controlefamiliabackend.dtos.ContaBancoDto;
import com.example.controlefamiliabackend.models.ContaBancoModel;
import com.example.controlefamiliabackend.repositories.UsuarioRepository;
import com.example.controlefamiliabackend.services.ContaBancoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping(path = "/conta-banco")
public class ContaBancoController {

    final ContaBancoService contaBancoService;

    @Autowired
    final UsuarioRepository usuarioRepository;

    public ContaBancoController(ContaBancoService contaBancoService, UsuarioRepository usuarioRepository){this.contaBancoService = contaBancoService;
        this.usuarioRepository = usuarioRepository;
    }

    @ResponseBody
    @PostMapping
    public ResponseEntity<Object> saveContaBanco(@RequestBody @Valid ContaBancoDto contaBancoDto){
        if(contaBancoService.verificaBanco(contaBancoDto.getCodigoBanco()) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Banco nao encontrado");
        }
        ContaBancoModel contaBancoModel = contaBancoDto.converter(usuarioRepository);
        BeanUtils.copyProperties(contaBancoDto, contaBancoModel);
//        contaBancoModel.setDtCadastro(LocalDateTime.now(ZoneId.of("UTF")));
        return ResponseEntity.status(HttpStatus.CREATED).body(contaBancoService.save(contaBancoModel));
    }

    @GetMapping
    public ResponseEntity<List<ContaBancoModel>> getAllContaBnco(){
        return ResponseEntity.status(HttpStatus.OK).body(contaBancoService.findAll());
    }
}
