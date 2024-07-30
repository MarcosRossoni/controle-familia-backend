package com.controller;

import com.controller.session.Session;
import com.controller.session.SessionModel;
import com.dao.MovimentoParcelaDAO;
import com.dto.MovimentoAtualizarCadastrarDTO;
import com.enumeration.LogEnum;
import com.enumeration.SituacaoMovimento;
import com.enumeration.TipoMovimento;
import com.orm.Categoria;
import com.orm.ContaBancaria;
import com.orm.Movimento;
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

    @Session
    SessionModel userSession;

    public void gerarParcelas(MovimentoAtualizarCadastrarDTO movimentoDTO){

        BigDecimal vlParcela;
        BigDecimal vlTotalMovimento;
        if (movimentoDTO.getFgValorParcela()) {
            vlTotalMovimento = movimentoDTO.getVlMovimento().multiply(BigDecimal.valueOf(movimentoDTO.getQtdParcelas()));
            vlParcela = movimentoDTO.getVlMovimento();
        } else {
            vlParcela = movimentoDTO.getVlMovimento().divide(BigDecimal.valueOf(movimentoDTO.getQtdParcelas()));
            vlTotalMovimento = movimentoDTO.getVlMovimento();
        }

        LocalDate dtVencimento = LocalDate.parse(movimentoDTO.getDtVencimento());
        LocalDate dtMovimento = LocalDate.parse(movimentoDTO.getDtMovimento());
        Categoria categoria = Categoria.findById(movimentoDTO.getCategoria().getIdCategoria());
        ContaBancaria contaBancaria = ContaBancaria.findById(movimentoDTO.getContaBancaria().getIdContaBancaria());

        Long idMovimento = movimentoParcelaDAO.nexValue("seq_movimento");

        for (Integer parcela = 1; parcela <= movimentoDTO.getQtdParcelas(); parcela++) {
            Movimento movimento = new Movimento();
            movimento.setIdMovimento(idMovimento);
            movimento.setNrParcela(parcela);
            movimento.setDtCadastro(LocalDateTime.now());

            converteMovimento(movimentoDTO, vlParcela, dtVencimento,
                    dtMovimento, vlTotalMovimento, categoria, contaBancaria, movimento);
            movimento.persist();
            dtVencimento = dtVencimento.plusMonths(1);
        }

        registrarLog(
                userSession.getUsuario(),
                "Adicionou movimento " + idMovimento.toString(),
                LogEnum.MOVIMENTO);

    }

    private void converteMovimento(MovimentoAtualizarCadastrarDTO movimentoDTO, BigDecimal vlParcela, LocalDate dtVencimento,
                                        LocalDate dtMovimento, BigDecimal vlTotalMovimento,
                                        Categoria categoria, ContaBancaria contaBancaria, Movimento movimento){

        movimento.setVlMovimento(vlParcela);
        if (movimentoDTO.getFgTipoMovimento().equals(TipoMovimento.DESPESA.ordinal())){
            movimento.setVlMovimento(vlParcela.negate());
        }

        if (movimentoDTO.getFgSituacaoMovimento().equals(SituacaoMovimento.LIQUIDADO.ordinal()) &&
                dtVencimento.isBefore(LocalDate.now())) {
            movimento.setFgSituacaoMovimento(SituacaoMovimento.LIQUIDADO);
        } else {
            movimento.setFgSituacaoMovimento(SituacaoMovimento.ABERTO);
        }

        movimento.setDsDescricao(movimentoDTO.getDsDescricao());
        movimento.setDtMovimento(dtMovimento);
        movimento.setDtVencimento(dtVencimento);
        movimento.setDtAlteracao(LocalDateTime.now());
        movimento.setQtdTotalParcelas(movimentoDTO.getQtdParcelas());
        movimento.setVlTotalMovimento(vlTotalMovimento);
        movimento.setFgConciliarAutomatico(movimentoDTO.getFgConciliarAutomatico());
        movimento.setFgTipoMovimento(TipoMovimento.values()[movimentoDTO.getFgTipoMovimento()]);
        movimento.setUsuario(userSession.getUsuario());
        movimento.setContaBancaria(contaBancaria);
        movimento.setCategoria(categoria);

    }
}
