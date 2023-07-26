package com.controller;

import com.controller.converter.ContaBancariaConverter;
import com.dto.ContaBancariaDTO;
import com.enumeration.LogEnum;
import com.orm.ContaBancaria;
import com.orm.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

@ApplicationScoped
@Transactional
public class ContaBancariaController extends GenericController{

    @Inject
    ContaBancariaConverter contaBancariaConverter;

    public ContaBancariaDTO cadastrarContaBancaria(ContaBancariaDTO contaBancariaDTO){

        ContaBancaria contaBancaria = contaBancariaConverter.dtoToOrm(contaBancariaDTO);
        contaBancaria.setDtCadastro(LocalDateTime.now());
        contaBancaria.setDtUltimaMovimentacao(LocalDateTime.now());
        contaBancaria.setUsuarioCadastro(Usuario.findById(1));
        contaBancaria.setUsuarioMovimentacao(Usuario.findById(1));
        contaBancaria.setVlSaldoAtual(contaBancaria.getVlSaldoIncial());
        contaBancaria.persist();
        registrarLog(
                Usuario.findById(1),
                "Criou conta bancaria " + contaBancaria.getIdContaBancaria().toString(),
                LogEnum.CONTA_BANCARIA);

        return contaBancariaConverter.ormToDto(contaBancaria);
    }

    public ContaBancariaDTO atualizarContaBancaria(ContaBancariaDTO contaBancariaDTO){

        ContaBancaria contaBancaria = ContaBancaria.findById(contaBancariaDTO.getIdContaBancaria());
        contaBancaria.setDtUltimaMovimentacao(LocalDateTime.now());
        contaBancaria.setUsuarioMovimentacao(Usuario.findById(1));
        contaBancaria.persist();
        registrarLog(
                Usuario.findById(1),
                "Alterou conta bancaria " + contaBancaria.getIdContaBancaria().toString(),
                LogEnum.CONTA_BANCARIA);

        return contaBancariaConverter.ormToDto(contaBancaria);
    }
}
