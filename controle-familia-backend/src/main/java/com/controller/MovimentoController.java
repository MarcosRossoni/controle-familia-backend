package com.controller;

import com.controller.converter.MovimentoConverter;
import com.dto.project.list.ListMovimentoProjectDTO;
import com.dto.MovimentoDTO;
import com.dto.project.projectdto.MovimentoProjectDTO;
import com.enumeration.LogEnum;
import com.enumeration.TipoMovimento;
import com.orm.Movimento;
import com.orm.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    public MovimentoDTO findByIdMovimento(Long idMovimento){
        Movimento movimento = Movimento.find("idMovimento = ?1", idMovimento)
                .firstResult();
        return movimentoConverter.ormToDto(movimento);
    }

    public List<ListMovimentoProjectDTO> listarMovimentos() {
        return Movimento.find("SELECT m.idMovimento, m.nrParcela, m.qtdTotalParcelas, m.dsDescricao, m.vlMovimento, " +
                        "m.dtMovimento, m.dtVencimento, m.fgConciliarAutomatico, m.categoria.dsDescricao, m.categoria.dsCor, " +
                        "m.contaBancaria.dsDescricao, m.contaBancaria.dsBanco FROM Movimento m WHERE m.usuario.idUsuario = ?1",
                        1)
                .project(ListMovimentoProjectDTO.class)
                .list();
    }
}
