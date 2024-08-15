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
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
            vlParcela = movimentoDTO.getVlMovimento()
                    .divide(BigDecimal.valueOf(movimentoDTO.getQtdParcelas()), 2, RoundingMode.HALF_EVEN);
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
                LogEnum.MOVIMENTO
        );

    }

    public void alterarParcela(MovimentoAtualizarCadastrarDTO movimentoDTO, Movimento movimento){
        String dsMensagemLog = " alterou o movimento";
        BigDecimal vlTotal = movimento.getVlTotalMovimento();
        List<Movimento> listMovimento = Movimento.find("idMovimento = ?1 AND nrParcela != ?2",
                movimentoDTO.getIdMovimento(), movimentoDTO.getNrParcela())
                .list();
        ContaBancaria contaBancaria = ContaBancaria.findById(movimentoDTO.getContaBancaria().getIdContaBancaria());

        if (movimentoDTO.getFgConciliarAutomatico().equals(movimento.getFgConciliarAutomatico())) {
            movimento.setFgConciliarAutomatico(movimentoDTO.getFgConciliarAutomatico());
            for (Movimento movimentoAlterados : listMovimento) {
                movimentoAlterados.setFgConciliarAutomatico(movimentoDTO.getFgConciliarAutomatico());
            }
            dsMensagemLog = dsMensagemLog.concat(", alterou conciliar automatico do movimento");
        }

        if (movimentoDTO.getFgSituacaoMovimento().equals(SituacaoMovimento.LIQUIDADO.ordinal())) {
            movimento.setFgSituacaoMovimento(SituacaoMovimento.LIQUIDADO);
            movimento.setDtBaixa(LocalDateTime.now());
            ajusteSaldoBanco(contaBancaria, movimentoDTO.getVlMovimento(), movimento.getFgTipoMovimento());
            dsMensagemLog = dsMensagemLog.concat(", alterou situacao do movimento");
        }

        if (movimentoDTO.getFgSituacaoMovimento().equals(SituacaoMovimento.ABERTO.ordinal()) &&
                movimento.getFgSituacaoMovimento().equals(SituacaoMovimento.LIQUIDADO)) {
            movimento.setFgSituacaoMovimento(SituacaoMovimento.ABERTO);
            movimento.setDtBaixa(null);
            ajusteSaldoBanco(contaBancaria, movimento.getVlMovimento(),
                    movimento.getFgTipoMovimento().equals(TipoMovimento.RECEITA) ?
                            TipoMovimento.DESPESA :
                            TipoMovimento.RECEITA);
            dsMensagemLog = dsMensagemLog.concat(", alterou situacao do movimento");
        }

        if (!movimentoDTO.getDsDescricao().equals(movimento.getDsDescricao())) {
            movimento.setDsDescricao(movimentoDTO.getDsDescricao());
            for (Movimento movimentoAlterados : listMovimento) {
                movimentoAlterados.setDsDescricao(movimentoDTO.getDsDescricao());
            }
            dsMensagemLog = dsMensagemLog.concat(", alterou descricao do movimento");
        }

        if (!movimentoDTO.getContaBancaria().getIdContaBancaria().equals(movimento.getContaBancaria().getIdContaBancaria())) {
            movimento.setContaBancaria(contaBancaria);
            for (Movimento movimentoAlterados : listMovimento) {
                movimentoAlterados.setContaBancaria(contaBancaria);
            }
            dsMensagemLog = dsMensagemLog.concat(", alterou conta bancaria do movimento");
        }

        if (!(movimentoDTO.getVlMovimento().divide(BigDecimal.ONE, 2, RoundingMode.HALF_EVEN))
                .equals(movimento.getVlMovimento().abs())) {
            vlTotal = vlTotal.subtract(movimento.getVlMovimento().abs()).add(movimentoDTO.getVlMovimento());
            BigDecimal vlMovimento = movimento.getFgTipoMovimento().equals(TipoMovimento.DESPESA) ?
                    movimentoDTO.getVlMovimento().negate() : movimentoDTO.getVlMovimento();

            movimento.setVlMovimento(vlMovimento);
            movimento.setVlTotalMovimento(vlTotal);
            for (Movimento movimentoAlterados : listMovimento) {
                movimentoAlterados.setVlTotalMovimento(vlTotal);
            }
            dsMensagemLog = dsMensagemLog.concat(", alterou o valor da parcela " + movimentoDTO.getNrParcela().toString());
        }

        if (!LocalDate.parse(movimentoDTO.getDtMovimento()).equals(movimento.getDtMovimento())) {
            LocalDate dtMovimento = LocalDate.parse(movimentoDTO.getDtMovimento());
            movimento.setDtMovimento(dtMovimento);
            for (Movimento movimentoAlterados : listMovimento) {
                movimentoAlterados.setDtMovimento(dtMovimento);
            }
            dsMensagemLog = dsMensagemLog.concat(", alterou data de movimento");
        }

        if (!LocalDate.parse(movimentoDTO.getDtVencimento()).equals(movimento.getDtVencimento())) {
            movimento.setDtVencimento(LocalDate.parse(movimentoDTO.getDtVencimento()));
            dsMensagemLog = dsMensagemLog.concat(", alterou data de vencimento");
        }

        if (!movimentoDTO.getCategoria().getIdCategoria().equals(movimento.getCategoria().getIdCategoria())) {
            Categoria categoria = Movimento.findById(movimentoDTO.getCategoria().getIdCategoria());
            movimento.setCategoria(categoria);
            for (Movimento movimentoAlterado : listMovimento) {
                movimentoAlterado.setCategoria(categoria);
            }
            dsMensagemLog = dsMensagemLog.concat(", alterou a categoria do movimento");
        }

        movimento.setDtAlteracao(LocalDateTime.now());
        movimento.persist();

        for (Movimento movimentoAlteradodo : listMovimento) {
            movimentoAlteradodo.setDtAlteracao(LocalDateTime.now());
            movimentoAlteradodo.persist();
        }

        registrarLog(
                userSession.getUsuario(),
                dsMensagemLog,
                LogEnum.MOVIMENTO
        );
    }

    //---

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
            ajusteSaldoBanco(contaBancaria, movimentoDTO.getVlMovimento(), TipoMovimento.values()[movimentoDTO.getFgTipoMovimento()]);
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

    private void ajusteSaldoBanco(ContaBancaria contaBancaria, BigDecimal vlMovimento, TipoMovimento fgTipoMovimento) {
        if (fgTipoMovimento.equals(TipoMovimento.DESPESA)) {
            BigDecimal vlAtt = contaBancaria.getVlSaldoAtual().subtract(vlMovimento);
            contaBancaria.setVlSaldoAtual(vlAtt);
        }
        if (fgTipoMovimento.equals(TipoMovimento.RECEITA)) {
            BigDecimal vlAtt = contaBancaria.getVlSaldoAtual().add(vlMovimento);
            contaBancaria.setVlSaldoAtual(vlAtt);
        }
        contaBancaria.persist();
    }
}
