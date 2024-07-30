package com.controller;

import com.controller.converter.MovimentoConverter;
import com.controller.session.Session;
import com.controller.session.SessionModel;
import com.dto.MovimentoAtualizarCadastrarDTO;
import com.dto.MovimentoDTO;
import com.dto.PaginacaoDTO;
import com.dto.filter.MovimentoFilterDTO;
import com.dto.project.list.ListMovimentoProjectDTO;
import com.enumeration.LogEnum;
import com.enumeration.TipoMovimento;
import com.orm.ContaBancaria;
import com.orm.Movimento;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
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

    @Session
    SessionModel userSession;

    public MovimentoDTO cadastroMovimento(MovimentoAtualizarCadastrarDTO movimentoDTO){

        movimentoParcelaController.gerarParcelas(movimentoDTO);
        return null;
    }

    public MovimentoDTO alterarMovimento(MovimentoAtualizarCadastrarDTO movimentoDTO){

        Movimento movimento = Movimento.find("idMovimento", movimentoDTO.getIdMovimento()).firstResult();

        if (movimento.getFgTipoMovimento().equals(TipoMovimento.DESPESA)){
            movimento.setVlMovimento(movimentoDTO.getVlMovimento().negate());
        }

        if (!movimentoDTO.getContaBancaria().getIdContaBancaria().equals(movimento.getContaBancaria().getIdContaBancaria())) {
            movimento.setContaBancaria(ContaBancaria.findById(movimentoDTO.getContaBancaria().getIdContaBancaria()));
        }

        movimento.setDtAlteracao(LocalDateTime.now());
        movimento.persist();
        registrarLog(
                userSession.getUsuario(),
                "Alterou movimento " + movimento.getIdMovimento().toString(),
                LogEnum.MOVIMENTO);

        return movimentoConverter.ormToDto(movimento);
    }

    public MovimentoDTO findByIdMovimento(Long idMovimento){
        Movimento movimento = Movimento.find("idMovimento = ?1", idMovimento)
                .firstResult();
        return movimentoConverter.ormToDto(movimento);
    }

    public PaginacaoDTO listarMovimentos(MovimentoFilterDTO movimentoFilterDTO) {

        String dsQuery;

        String dsSelect = """
                SELECT m.idMovimento, m.nrParcela, m.qtdTotalParcelas, m.dsDescricao, m.vlMovimento,
                                                m.dtMovimento, m.dtVencimento, m.fgConciliarAutomatico, m.categoria.dsDescricao, m.categoria.dsCor,
                                                m.contaBancaria.dsDescricao, m.contaBancaria.dsBanco, m.fgSituacaoMovimento
                """;

        String dsFrom = "FROM Movimento m ";
        String dsWhere = getDsWhere(movimentoFilterDTO);

        dsQuery = dsSelect + dsFrom + dsWhere;

        PanacheQuery<ListMovimentoProjectDTO> listPaginacao = Movimento.find(dsQuery,
                        userSession.getUsuario().getIdUsuario().toString())
                .project(ListMovimentoProjectDTO.class);
        return new PaginacaoDTO(listPaginacao, movimentoFilterDTO);
    }

    private static String getDsWhere(MovimentoFilterDTO movimentoFilterDTO) {
        String dsWhere = "WHERE m.usuario.idUsuario = ?1";

        if (movimentoFilterDTO.getDtInicio() != null) {
            dsWhere += (" AND (cast(m.dtVencimento AS date) BETWEEN cast('#1' AS date) AND cast('#2' AS date))")
                    .replace("#1", movimentoFilterDTO.getDtInicio())
                    .replace("#2", movimentoFilterDTO.getDtFim());
        }
        if (movimentoFilterDTO.getIdCategoria() != null) {
            dsWhere += (" AND m.categoria.idCategoria = #1")
                    .replace("#1", movimentoFilterDTO.getIdCategoria().toString());
        }
        if (movimentoFilterDTO.getFgTipoMovimento() != null) {
            dsWhere += "AND m.fgTipoMovimento = " + movimentoFilterDTO.getFgTipoMovimento();
        }
        return dsWhere;
    }
}
