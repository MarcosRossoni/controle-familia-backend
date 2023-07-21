package com.controller;

import com.controller.converter.MovimentoConverter;
import com.dto.MovimentoDTO;
import com.enumeration.TipoMovimento;
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

    public MovimentoDTO cadastroMovimento(MovimentoDTO movimentoDTO){

        Movimento movimento = movimentoConverter.dtoToOrm(movimentoDTO);

        if (movimento.getFgTipoMovimento().equals(TipoMovimento.DESPESA)){
            movimento.setVlMovimento(movimentoDTO.getVlMovimento().negate());
        }

        movimento.setDtCadastro(LocalDateTime.now());
        movimento.setDtAlteracao(LocalDateTime.now());
        movimento.setUsuario(Usuario.findById(6));
        movimento.persist();
        return movimentoConverter.ormToDto(movimento);
    }

    public MovimentoDTO alterarMovimento(MovimentoDTO movimentoDTO){

        Movimento movimento = Movimento.findById(movimentoDTO.getIdMovimento());

        return null;

    }
}
