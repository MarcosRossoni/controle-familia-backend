package com.controller.converter;

import com.dto.MovimentoDTO;
import com.enumeration.TipoMovimento;
import com.orm.Movimento;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;

@ApplicationScoped
public class MovimentoConverter extends GenericConverter<Movimento, MovimentoDTO>{

    @Inject
    UsuarioConverter usuarioConverter;

    @Inject
    ContaBancariaConverter contaBancariaConverter;

    @Override
    public MovimentoDTO ormToDto(Movimento movimento) {
        return ormToDto(movimento, new MovimentoDTO());
    }

    @Override
    public Movimento dtoToOrm(MovimentoDTO movimentoDTO) {
        return dtoToOrm(movimentoDTO, new Movimento());
    }

    @Override
    public MovimentoDTO ormToDto(Movimento movimento, MovimentoDTO movimentoDTO) {

        copy(movimento, movimentoDTO);
        movimentoDTO.setUsuario(usuarioConverter.ormToDto(movimento.getUsuario()));
        movimentoDTO.setContaBancaria(contaBancariaConverter.ormToDto(movimento.getContaBancaria()));
        movimentoDTO.setFgTipoMovimento(movimento.getFgTipoMovimento().ordinal());
        movimentoDTO.setDtMovimento(movimento.getDtMovimento().toString());
        movimentoDTO.setDtCadastro(movimento.getDtCadastro().toString());
        movimentoDTO.setDtAlteracao(movimento.getDtAlteracao().toString());
        return movimentoDTO;
    }

    @Override
    public Movimento dtoToOrm(MovimentoDTO movimentoDTO, Movimento movimento) {

        copy(movimentoDTO, movimento);
        movimento.setDtMovimento(LocalDate.parse(movimentoDTO.getDtMovimento()));
        movimento.setDtVencimento(LocalDate.parse(movimentoDTO.getDtVencimento()));
        movimento.setFgTipoMovimento(TipoMovimento.values()[movimentoDTO.getFgTipoMovimento()]);
        return movimento;
    }

    @Override
    protected String[] ignoreProperties() {
        return new String[]{"usuario", "fgTipoMovimento", "dtMovimento", "dtCadastro", "dtAlteracao", "contaBancaria"};
    }
}
