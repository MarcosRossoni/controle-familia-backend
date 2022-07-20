package com.example.controlefamiliabackend.controllers;


import com.example.controlefamiliabackend.dtos.ContaBancoDto;
import com.example.controlefamiliabackend.enuns.CodigoBancos;
import com.example.controlefamiliabackend.models.ContaBancoModel;
import com.example.controlefamiliabackend.models.UsuarioModel;
import com.example.controlefamiliabackend.services.ContaBancoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/conta-banco")
public class ContaBancoController {

    final ContaBancoService contaBancoService;

    public ContaBancoController(ContaBancoService contaBancoService){this.contaBancoService = contaBancoService; }

    @ResponseBody
    @PostMapping
    public ResponseEntity<Object> saveContaBanco(@RequestMapping @Valid ContaBancoDto contaBancoDto){
        ContaBancoModel contaBancoModel = new ContaBancoModel();
        BeanUtils.copyProperties(contaBancoDto, contaBancoModel);
        contaBancoModel.setDsBanco(CodigoBancos);
    }
}
