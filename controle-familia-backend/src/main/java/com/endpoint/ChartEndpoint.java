package com.endpoint;

import com.controller.ChartController;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONArray;

@Path("/chart")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChartEndpoint {

    @Inject
    ChartController chartController;

    @GET
    @Path("/find")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getChart() {
        JSONArray charts = chartController.buscarCharts();
        return Response.ok(charts).build();
    }
}
