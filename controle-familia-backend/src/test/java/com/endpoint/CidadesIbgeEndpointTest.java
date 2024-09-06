package com.endpoint;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

@QuarkusTest
@Tag("integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CidadesIbgeEndpointTest {

    @Test
    @Order(1)
    void buscarCidadeTest() {
        given()
                .when()
                .get("/cidades/buscar-cidade/Alta Floresta D'Oeste")
                .then()
                .statusCode(200);
    }
}