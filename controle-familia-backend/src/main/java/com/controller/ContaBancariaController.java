package com.controller;

import com.controller.converter.ContaBancariaConverter;
import com.dto.ContaBancariaDTO;
import com.dto.project.list.ListContasBancariasProjectDTO;
import com.enumeration.LogEnum;
import com.orm.ContaBancaria;
import com.orm.Usuario;
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

    public ContaBancariaDTO cadastrarContaBancaria(ContaBancariaDTO contaBancariaDTO){

        ContaBancaria contaBancaria = contaBancariaConverter.dtoToOrm(contaBancariaDTO);
        contaBancaria.setFgAtiva(true);
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
        contaBancariaConverter.dtoToOrm(contaBancariaDTO, contaBancaria);
        contaBancaria.setDtUltimaMovimentacao(LocalDateTime.now());
        contaBancaria.setUsuarioMovimentacao(Usuario.findById(1));
        contaBancaria.persist();
        registrarLog(
                Usuario.findById(1),
                "Alterou conta bancaria " + contaBancaria.getIdContaBancaria().toString(),
                LogEnum.CONTA_BANCARIA);

        return contaBancariaConverter.ormToDto(contaBancaria);
    }

    public ContaBancariaDTO findById(Integer idConta) {
        ContaBancaria contaBancaria = ContaBancaria.findById(idConta);
        return contaBancariaConverter.ormToDto(contaBancaria);
    }

    public List<ListContasBancariasProjectDTO> findAll() {
        return ContaBancaria.find("usuarioCadastro.idUsuario = 1", Sort.ascending("dsDescricao"))
                .project(ListContasBancariasProjectDTO.class)
                .list();
    }

    public List<ListContasBancariasProjectDTO> autoCompleteContaBancaria(String param){
        return ContaBancaria.find("LOWER(dsDescricao) LIKE LOWER(?1) AND usuarioCadastro.idUsuario = 1 AND fgAtiva = true",
                        Sort.ascending("dsDescricao"), "%" + param + "%")
                .project(ListContasBancariasProjectDTO.class)
                .list();
    }
}
