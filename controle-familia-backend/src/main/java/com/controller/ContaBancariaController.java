package com.controller;

import com.controller.converter.ContaBancariaConverter;
import com.controller.session.Session;
import com.controller.session.SessionModel;
import com.dto.contabancaria.ContaBancariaDTO;
import com.dto.contabancaria.project.ListContasBancariasProjectDTO;
import com.enumeration.LogEnum;
import com.orm.ContaBancaria;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
@Transactional
public class ContaBancariaController extends GenericController{

    @Inject
    ContaBancariaConverter contaBancariaConverter;

    @Session
    SessionModel userSession;

    public ContaBancariaDTO cadastrarContaBancaria(ContaBancariaDTO contaBancariaDTO){

        ContaBancaria contaBancaria = contaBancariaConverter.dtoToOrm(contaBancariaDTO);
        contaBancaria.setFgAtiva(true);
        contaBancaria.setDtCadastro(LocalDateTime.now());
        contaBancaria.setDtUltimaMovimentacao(LocalDateTime.now());
        contaBancaria.setUsuarioCadastro(userSession.getUsuario());
        contaBancaria.setUsuarioMovimentacao(userSession.getUsuario());
        contaBancaria.setVlSaldoAtual(contaBancaria.getVlSaldoIncial());
        contaBancaria.persist();
        registrarLog(
                userSession.getUsuario(),
                "Criou conta bancaria " + contaBancaria.getIdContaBancaria().toString(),
                LogEnum.CONTA_BANCARIA);

        return contaBancariaConverter.ormToDto(contaBancaria);
    }

    public ContaBancariaDTO atualizarContaBancaria(ContaBancariaDTO contaBancariaDTO){

        ContaBancaria contaBancaria = ContaBancaria.findById(contaBancariaDTO.getIdContaBancaria());
        contaBancariaConverter.dtoToOrm(contaBancariaDTO, contaBancaria);
        contaBancaria.setDtUltimaMovimentacao(LocalDateTime.now());
        contaBancaria.setUsuarioMovimentacao(userSession.getUsuario());
        contaBancaria.persist();
        registrarLog(
                userSession.getUsuario(),
                "Alterou conta bancaria " + contaBancaria.getIdContaBancaria().toString(),
                LogEnum.CONTA_BANCARIA);

        return contaBancariaConverter.ormToDto(contaBancaria);
    }

    public ContaBancariaDTO findById(Integer idConta) {
        ContaBancaria contaBancaria = ContaBancaria.findById(idConta);
        return contaBancariaConverter.ormToDto(contaBancaria);
    }

    public List<ListContasBancariasProjectDTO> findAll() {
        return ContaBancaria.find("usuarioCadastro.idUsuario = ?1",
                        Sort.ascending("dsDescricao"),
                        userSession.getUsuario().getIdUsuario().toString())
                .project(ListContasBancariasProjectDTO.class)
                .list();
    }

    public List<ListContasBancariasProjectDTO> autoCompleteContaBancaria(String param){
        return ContaBancaria.find("LOWER(dsDescricao) LIKE LOWER(?1) AND usuarioCadastro.idUsuario = ?2 AND fgAtiva = true",
                        Sort.ascending("dsDescricao"), "%" + param + "%",
                        userSession.getUsuario().getIdUsuario().toString())
                .project(ListContasBancariasProjectDTO.class)
                .list();
    }
}
