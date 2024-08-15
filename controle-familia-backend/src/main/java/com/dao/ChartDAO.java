package com.dao;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
@Transactional
public class ChartDAO extends PanacheEntity {

    public JSONArray buscarTotalCategoria(LocalDate dtInicio, LocalDate dtFim, Long idCategoria) {
        List<Object[]> listCategoria = getEntityManager().createNativeQuery(
                        "SELECT date_part('day', m.dt_vencimento), SUM(m.vl_movimento), c.ds_cor " +
                                "FROM movimento m " +
                                "LEFT JOIN categoria c on c.id_categoria = m.id_categoria " +
                                "WHERE c.id_categoria = :idCategoria AND m.dt_vencimento BETWEEN :dtInicio " +
                                "AND :dtFim GROUP BY date_part('day', m.dt_vencimento), c.ds_cor ")
                .setParameter("dtInicio", dtInicio)
                .setParameter("dtFim", dtFim)
                .setParameter("idCategoria", idCategoria)
                .getResultList();

        JSONArray jsonArray = new JSONArray();

        for (Object[] objects : listCategoria) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("dia", objects[0]);
            jsonObject.put("valor", objects[1]);
            jsonObject.put("dsCor", objects[2]);
            jsonArray.put(jsonObject);
        }

        return jsonArray;
    }

    public JSONArray buscarTotalAgrupadoCategoria(LocalDate dtInicio, LocalDate dtFim) {
        List<Object[]> listCategoria = getEntityManager().createNativeQuery(
                "SELECT c.ds_descricao, coalesce(sum(m.vl_movimento), 0), c.ds_cor " +
                        "FROM movimento m " +
                        "LEFT JOIN categoria c on c.id_categoria = m.id_categoria " +
                        "WHERE m.dt_vencimento BETWEEN :dtInicio AND :dtFim GROUP BY c.ds_descricao, m.dt_vencimento, c.ds_cor")
                .setParameter("dtInicio", dtInicio)
                .setParameter("dtFim", dtFim)
                .getResultList();

        JSONArray jsonArray = new JSONArray();
        for (Object[] objects : listCategoria) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("dsCategoria", objects[0]);
            jsonObject.put("valor", objects[1]);
            jsonObject.put("dsCor", objects[2]);
            jsonArray.put(jsonObject);
        }

        return jsonArray;
    }
}
