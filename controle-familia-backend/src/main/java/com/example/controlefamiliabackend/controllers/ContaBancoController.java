package com.example.controlefamiliabackend.controllers;


import com.example.controlefamiliabackend.dtos.ContaBancoDto;
import com.example.controlefamiliabackend.forms.AtualizacaoContaBancoForm;
import com.example.controlefamiliabackend.forms.ContaBancoForm;
import com.example.controlefamiliabackend.models.ContaBancoModel;
import com.example.controlefamiliabackend.repositories.ContaBancoRepository;
import com.example.controlefamiliabackend.repositories.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigInteger;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/conta-banco")
public class ContaBancoController {

    final UsuarioRepository usuarioRepository;
    final ContaBancoRepository contaBancoRepository;

    public ContaBancoController(UsuarioRepository usuarioRepository,
                                ContaBancoRepository contaBancoRepository){
        this.usuarioRepository = usuarioRepository;
        this.contaBancoRepository = contaBancoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ContaBancoDto> saveContaBanco(@RequestBody @Valid ContaBancoForm contaBancoForm,
                                                        UriComponentsBuilder uriComponentsBuilder){
        ContaBancoModel contaBancoModel = contaBancoForm.converter(usuarioRepository);
        URI uri = uriComponentsBuilder.path("/conta-banco/{id}").buildAndExpand(contaBancoModel
                .getIdContaBancaria()).toUri();
        contaBancoRepository.save(contaBancoModel);
        return ResponseEntity.created(uri).body(new ContaBancoDto(contaBancoModel));
    }

    @GetMapping
    public List<ContaBancoDto> getAllContaBanco(){
        List<ContaBancoModel> contaBanco = contaBancoRepository.findAll();
        return ContaBancoDto.converterList(contaBanco);
    }

    @GetMapping("{id}")
    public ContaBancoDto getContaBancaria(@PathVariable(value = "id") BigInteger id){
        ContaBancoModel contaBancoModel = contaBancoRepository.getReferenceById(id);
        return ContaBancoDto.converter(contaBancoModel);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> deleteContaBanco(@PathVariable(value = "id") BigInteger id){
        contaBancoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ContaBancoDto> atualizarContaBanco(@PathVariable(value = "id") BigInteger id,
                                                             @RequestBody @Valid
                                                             AtualizacaoContaBancoForm atualizacaoContaBancoForm){
        ContaBancoModel contaBancoModel = atualizacaoContaBancoForm.atualizar(id, contaBancoRepository, usuarioRepository);
        return ResponseEntity.ok(new ContaBancoDto(contaBancoModel));

    }
}
