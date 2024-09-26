package com.controller.converter;

import com.dto.contabancaria.ContaBancariaDTO;
import com.enumeration.TipoContaBancaria;
import com.orm.ContaBancaria;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContaBancariaConverter extends GenericConverter<ContaBancaria, ContaBancariaDTO>{

    @Override
    public ContaBancariaDTO ormToDto(ContaBancaria contaBancaria) {
        return ormToDto(contaBancaria, new ContaBancariaDTO());
    }

    @Override
    public ContaBancaria dtoToOrm(ContaBancariaDTO contaBancariaDTO) {
        return dtoToOrm(contaBancariaDTO, new ContaBancaria());
    }

    @Override
    public ContaBancariaDTO ormToDto(ContaBancaria contaBancaria, ContaBancariaDTO contaBancariaDTO) {

        copy(contaBancaria, contaBancariaDTO);

        contaBancariaDTO.setFgContaBancaria(contaBancaria.getFgContaBancaria().ordinal());
        contaBancariaDTO.setDtUltimaMovimentacao(contaBancaria.getDtUltimaMovimentacao().toString());

        return contaBancariaDTO;
    }

    @Override
    public ContaBancaria dtoToOrm(ContaBancariaDTO contaBancariaDTO, ContaBancaria contaBancaria) {

        copy(contaBancariaDTO, contaBancaria);
        contaBancaria.setFgContaBancaria(TipoContaBancaria.values()[contaBancariaDTO.getFgContaBancaria()]);
        return contaBancaria;
    }

    @Override
    protected String[] ignoreProperties() {
        return new String[]{"usuarioCadastro", "fgContaBancaria", "usuarioMovimentacao"};
    }
}
