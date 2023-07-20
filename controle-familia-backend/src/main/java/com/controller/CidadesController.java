package com.controller;

import com.dto.project.ListCidadesProjectDTO;
import com.orm.Cidade;
import com.restclient.IbgeRestClient;
import io.quarkus.panache.common.Sort;
import io.vertx.core.json.JsonArray;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
@Transactional
public class CidadesController {

    @Inject
    @RestClient
    IbgeRestClient ibgeRestClient;

    public void cadastroCidade(){
        JsonArray jsonArray = ibgeRestClient.buscarCidades();
        for (int i = 0; i < jsonArray.size(); i++) {
            Cidade cidade = new Cidade();
            cidade.setIdCidade(jsonArray.getJsonObject(i).getLong("id"));
            cidade.setDsNome(jsonArray.getJsonObject(i).getString("nome"));
            cidade.setDsSiglaEstado(jsonArray.getJsonObject(i)
                    .getJsonObject("microrregiao")
                    .getJsonObject("mesorregiao")
                    .getJsonObject("UF")
                    .getString("sigla"));
            cidade.setDsNomeEstado(jsonArray.getJsonObject(i)
                    .getJsonObject("microrregiao")
                    .getJsonObject("mesorregiao")
                    .getJsonObject("UF")
                    .getString("nome"));
            cidade.setDsRegiao(jsonArray.getJsonObject(i)
                    .getJsonObject("microrregiao")
                    .getJsonObject("mesorregiao")
                    .getJsonObject("UF")
                    .getJsonObject("regiao")
                    .getString("nome"));
            cidade.persist();
        }
    }

    public List<ListCidadesProjectDTO> autoCompleteCidade(String param){
        return Cidade.find("LOWER(dsNome) LIKE LOWER(?1)", Sort.ascending("dsNome"), "%" + param + "%")
                .project(ListCidadesProjectDTO.class)
                .list();
    }
}
