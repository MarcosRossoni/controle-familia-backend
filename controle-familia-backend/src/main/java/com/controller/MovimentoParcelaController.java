package com.controller;

import com.controller.converter.MovimentoConverter;
import com.dao.MovimentoParcelaDAO;
import com.dto.MovimentoDTO;
import com.enumeration.LogEnum;
import com.enumeration.TipoMovimento;
import com.orm.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ApplicationScoped
@Transactional
public class MovimentoParcelaController extends GenericController{

    @Inject
    MovimentoParcelaDAO movimentoParcelaDAO;

    @Inject
    MovimentoConverter movimentoConverter;

    public void gerarParcelas(MovimentoDTO movimentoDTO){

        BigDecimal vlParcela = movimentoDTO.getVlMovimento().divide(BigDecimal.valueOf(movimentoDTO.getQtdParcelas()));
        LocalDate dtVencimento = LocalDate.parse(movimentoDTO.getDtVencimento());

        Long idMovimento = movimentoParcelaDAO.nexValue("seq_movimento");

        for (Integer parcela = 1; parcela <= movimentoDTO.getQtdParcelas(); parcela++) {
            Movimento movimento = converteMovimento(movimentoDTO, vlParcela, dtVencimento, parcela, idMovimento);
            movimento.persist();
            dtVencimento = dtVencimento.plusMonths(1);
        }

        registrarLog(
                Usuario.findById(1),
                "Adicionou movimento " + idMovimento.toString(),
                LogEnum.MOVIMENTO);

    }

    private Movimento converteMovimento(MovimentoDTO movimentoDTO, BigDecimal vlParcela, LocalDate dtVencimento,
                                        Integer nrParcela, Long idMovimento){

        Movimento movimento = movimentoConverter.dtoToOrm(movimentoDTO);

        movimento.setVlMovimento(vlParcela);
        if (movimento.getFgTipoMovimento().equals(TipoMovimento.DESPESA)){
            movimento.setVlMovimento(vlParcela.negate());
        }

        movimento.setIdMovimento(idMovimento);
        movimento.setNrParcela(nrParcela);
        movimento.setDtVencimento(dtVencimento);
        movimento.setDtCadastro(LocalDateTime.now());
        movimento.setDtAlteracao(LocalDateTime.now());
        movimento.setUsuario(Usuario.findById(1));
        movimento.setContaBancaria(ContaBancaria.findById(movimentoDTO.getContaBancaria().getIdContaBancaria()));
        movimento.setCategoria(Categoria.findById(movimentoDTO.getCategoria().getIdCategoria()));
        movimento.setQtdTotalParcelas(movimentoDTO.getQtdParcelas());
        return movimento;

    }
}
