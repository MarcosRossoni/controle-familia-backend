package com.controller;

import com.controller.converter.MovimentoConverter;
import com.dto.MovimentoDTO;
import com.enumeration.LogEnum;
import com.enumeration.TipoMovimento;
import com.orm.ContaBancaria;
import com.orm.Movimento;
import com.orm.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

@ApplicationScoped
@Transactional
public class MovimentoController extends GenericController{

    @Inject
    MovimentoConverter movimentoConverter;

    @Inject
    MovimentoParcelaController movimentoParcelaController;

    public MovimentoDTO cadastroMovimento(MovimentoDTO movimentoDTO){

        movimentoParcelaController.gerarParcelas(movimentoDTO);
        return null;
    }

    public MovimentoDTO alterarMovimento(MovimentoDTO movimentoDTO){

        Movimento movimento = Movimento.findById(movimentoDTO.getIdMovimento());

        movimentoConverter.dtoToOrm(movimentoDTO, movimento);

        if (movimento.getFgTipoMovimento().equals(TipoMovimento.DESPESA)){
            movimento.setVlMovimento(movimentoDTO.getVlMovimento().negate());
        }

        movimento.setDtAlteracao(LocalDateTime.now());
        movimento.persist();
        registrarLog(
                Usuario.findById(1),
                "Alterou movimento " + movimento.getIdMovimento().toString(),
                LogEnum.MOVIMENTO);

        return movimentoConverter.ormToDto(movimento);
    }
}
