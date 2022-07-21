package com.example.controlefamiliabackend.services;

import com.example.controlefamiliabackend.enuns.CodigoBancos;
import com.example.controlefamiliabackend.models.ContaBancoModel;
import com.example.controlefamiliabackend.models.UsuarioModel;
import com.example.controlefamiliabackend.repositories.ContaBancoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ContaBancoService {

    final ContaBancoRepository contaBancoRepository;

    public ContaBancoService(ContaBancoRepository contaBancoRepository){
        this.contaBancoRepository = contaBancoRepository;
    }

    @Transactional
    public ContaBancoModel save(ContaBancoModel contaBancoModel){

        return contaBancoRepository.save(contaBancoModel);
    }

    public String verificaBanco(String codBanco){
        if(CodigoBancos.shortName(codBanco) != null){
            return codBanco;
        }
        return null;
    }

    public List<ContaBancoModel> findAll(){
        return contaBancoRepository.findAll();
    }
}
