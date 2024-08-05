package com.controller;

import com.controller.converter.MovimentoConverter;
import com.controller.session.Session;
import com.controller.session.SessionModel;
import com.dto.MovimentoAtualizarCadastrarDTO;
import com.dto.MovimentoDTO;
import com.dto.PaginacaoDTO;
import com.dto.filter.MovimentoFilterDTO;
import com.dto.project.list.ListMovimentoProjectDTO;
import com.enumeration.SituacaoMovimento;
import com.exception.BadRequestException;
import com.orm.Movimento;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.OrderBy;
import jakarta.persistence.criteria.Order;
import jakarta.transaction.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

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

        Movimento movimento = Movimento.find("idMovimento = ?1 AND nrParcela = ?2",
                movimentoDTO.getIdMovimento(), movimentoDTO.getNrParcela())
                .firstResult();

        if (movimento.getFgSituacaoMovimento().equals(SituacaoMovimento.LIQUIDADO) &&
                movimentoDTO.getFgSituacaoMovimento().equals(SituacaoMovimento.LIQUIDADO.ordinal())) {
            throw new BadRequestException("Situação liquidado não pode ser alterada.");
        }

        movimentoParcelaController.alterarParcela(movimentoDTO, movimento);
        return null;
    }

    public MovimentoDTO findByIdMovimento(Long idMovimento, Long nrParcela){

        Movimento movimento = Movimento.find("idMovimento = ?1 AND nrParcela = ?2", idMovimento, nrParcela)
                .firstResult();
        return movimentoConverter.ormToDto(movimento);
    }

    public void deletMovimento(Long idMovimento){
        long qtdMovimentoLiquidado = Movimento.find("idMovimento = ?1 AND fgSituacaoMovimento = ?2",
                idMovimento, SituacaoMovimento.LIQUIDADO)
                .count();

        if (qtdMovimentoLiquidado > 0) {
            throw new BadRequestException("Existe parcelas do movimento que estão liquidadas, não pode ser deletado.");
        }

        Movimento.delete("idMovimento = ?1", idMovimento);
    }

    public PaginacaoDTO listarMovimentos(MovimentoFilterDTO movimentoFilterDTO) {

        String dsQuery;

        String dsWhere = "WHERE m.usuario.idUsuario = :idUsuario ";

        Map<String, Object> parametros = new LinkedHashMap<>();
        parametros.put("idUsuario", userSession.getUsuario().getIdUsuario());

        if (movimentoFilterDTO.getDtInicio() != null) {
            dsWhere += " AND (cast(m.dtVencimento AS date) BETWEEN cast(:dtInicio AS date) AND cast(:dtFim AS date))";
            parametros.put("dtInicio", movimentoFilterDTO.getDtInicio());
            parametros.put("dtFim", movimentoFilterDTO.getDtFim());
        }
        if (movimentoFilterDTO.getIdCategoria() != null) {
            dsWhere += " AND m.categoria.idCategoria = :idCategoria";
            parametros.put("idCategoria", movimentoFilterDTO.getIdCategoria());
        }
        if (movimentoFilterDTO.getFgTipoMovimento() != null) {
            dsWhere += "AND m.fgTipoMovimento = :fgTipoMovimento";
            parametros.put("fgTipoMovimento", movimentoFilterDTO.getFgTipoMovimento());
        }

        String dsSelect = """
                SELECT m.idMovimento, m.nrParcela, m.qtdTotalParcelas, m.dsDescricao, m.vlMovimento,
                                                m.dtMovimento, m.dtVencimento, m.fgConciliarAutomatico, m.categoria.dsDescricao, m.categoria.dsCor,
                                                m.contaBancaria.dsDescricao, m.contaBancaria.dsBanco, m.fgSituacaoMovimento
                """;

        String dsFrom = "FROM Movimento m ";

        dsQuery = dsSelect + dsFrom + dsWhere;

        Sort sort = Sort.ascending("dtVencimento");

        PanacheQuery<ListMovimentoProjectDTO> listPaginacao = Movimento
                .find(dsQuery, sort, parametros)
                .project(ListMovimentoProjectDTO.class);

        return new PaginacaoDTO(listPaginacao, movimentoFilterDTO);
    }
}
