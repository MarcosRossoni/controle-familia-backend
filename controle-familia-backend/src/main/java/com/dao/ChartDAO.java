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

    public JSONArray buscarTotalCategoria(LocalDate dtInicio, LocalDate dtFim, Long idCategoria, Long idUsuario) {
        List<Object[]> listCategoria = getEntityManager().createNativeQuery(
                        "SELECT date_part('day', m.dt_vencimento), SUM(m.vl_movimento), c.ds_cor " +
                                "FROM movimento m " +
                                "LEFT JOIN categoria c on c.id_categoria = m.id_categoria " +
                                "LEFT JOIN usuario u ON c.id_usuario = u.id_usuario " +
                                "WHERE c.id_categoria = :idCategoria AND m.dt_vencimento BETWEEN :dtInicio " +
                                "AND :dtFim AND u.id_usuario = :idUsuario " +
                                "GROUP BY date_part('day', m.dt_vencimento), c.ds_cor ")
                .setParameter("dtInicio", dtInicio)
                .setParameter("dtFim", dtFim)
                .setParameter("idCategoria", idCategoria)
                .setParameter("idUsuario", idUsuario)
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

    public JSONArray buscarTotalAgrupadoCategoria(LocalDate dtInicio, LocalDate dtFim, Long idUsuario) {
        List<Object[]> listCategoria = getEntityManager().createNativeQuery(
                "SELECT c.ds_descricao, coalesce(sum(m.vl_movimento), 0), c.ds_cor " +
                        "FROM movimento m " +
                        "LEFT JOIN categoria c ON c.id_categoria = m.id_categoria " +
                        "LEFT JOIN usuario u ON c.id_usuario = u.id_usuario " +
                        "WHERE m.dt_vencimento BETWEEN :dtInicio AND :dtFim AND u.id_usuario = :idUsuario " +
                        "GROUP BY c.ds_descricao, c.ds_cor")
                .setParameter("dtInicio", dtInicio)
                .setParameter("dtFim", dtFim)
                .setParameter("idUsuario", idUsuario)
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
