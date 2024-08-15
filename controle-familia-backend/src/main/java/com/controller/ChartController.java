package com.controller;

import com.dao.ChartDAO;
import com.orm.Categoria;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@ApplicationScoped
public class ChartController extends GenericController{

    @Inject
    ChartDAO chartDAO;

    public JSONArray buscarCharts() {
        LocalDate today = LocalDate.now().withMonth(LocalDate.now().getMonthValue());
        YearMonth anoMes = YearMonth.of(today.getYear(), today.getMonth().getValue());
        LocalDate dtInicial = anoMes.atDay(1);
        LocalDate dtFinal = anoMes.atEndOfMonth();
        JSONArray charts = new JSONArray();
        buscarChartCategorias(dtInicial, dtFinal, charts);
        buscarChartCategoriaGeral(dtInicial, dtFinal, charts);
        return charts;
    }

    //---

    private void buscarChartCategorias(LocalDate dtInicial, LocalDate dtFinal, JSONArray listCharts) {
        List<Categoria> listCategorias = Categoria.findAll().list();

        for (Categoria categoria : listCategorias) {
            JSONArray objects = chartDAO.buscarTotalCategoria(dtInicial, dtFinal, categoria.getIdCategoria());
            JSONArray labels = new JSONArray();
            JSONArray values = new JSONArray();
            JSONObject data = new JSONObject();
            JSONObject graficData = new JSONObject();
            JSONArray dataSets = new JSONArray();
            JSONObject objectChart = new JSONObject();

            objectChart.put("type", "line");
            objectChart.put("title", categoria.getDsDescricao());
            for (int i = dtInicial.getDayOfMonth(); i <= dtFinal.getDayOfMonth(); i++) {
                String label = Integer.toString(i);
                BigDecimal value = BigDecimal.ZERO;
                for (int i1 = 0; i1 < objects.length(); i1++) {
                    JSONObject object = objects.getJSONObject(i1);
                    if (object.getDouble("dia") == i) {
                        value = BigDecimal.valueOf(object.getDouble("valor")).abs();
                    }
                }
                labels.put(label);
                values.put(value);
            }

            graficData.put("label", categoria.getDsDescricao());
            graficData.put("data", values);
            graficData.put("fill", false);
            graficData.put("borderColor", categoria.getDsCor());
            graficData.put("tension", 0.1);

            dataSets.put(graficData);
            data.put("datasets", dataSets);
            data.put("labels", labels);

            objectChart.put("data", data);
            listCharts.put(objectChart);
        }
    }

    private void buscarChartCategoriaGeral(LocalDate dtInicial, LocalDate dtFinal, JSONArray listCharts) {
        JSONArray objects = chartDAO.buscarTotalAgrupadoCategoria(dtInicial, dtFinal);
        JSONObject graficData = new JSONObject();
        JSONArray dataSets = new JSONArray();
        JSONObject data = new JSONObject();
        JSONObject dataSetObj = new JSONObject();

        graficData.put("type", "pie");
        graficData.put("title", "Categorias");

        JSONArray labels = new JSONArray();
        JSONArray values = new JSONArray();
        JSONArray colors = new JSONArray();

        for (int i = 0; i < objects.length(); i++) {

            labels.put(objects.getJSONObject(i).getString("dsCategoria"));
            values.put(BigDecimal.valueOf(objects.getJSONObject(i).getDouble("valor")).abs());
            colors.put(objects.getJSONObject(i).getString("dsCor"));
        }

        dataSetObj.put("label", "Categorias");
        dataSetObj.put("data", values);
        dataSetObj.put("backgroundColor", colors);
        dataSetObj.put("hoverOffset", 4);
        dataSets.put(dataSetObj);

        data.put("labels", labels);
        data.put("datasets", dataSets);
        graficData.put("data", data);
        listCharts.put(graficData);
    }
}
