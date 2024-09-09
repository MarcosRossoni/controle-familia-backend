package com.endpoint;

import com.login.Login;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;

@QuarkusTest
@Tag("integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoriaEndpointTest {

    @Inject
    Login login;

    private static final JsonObject USUARIO_CADASTRO_CATEGORIA = Json.createObjectBuilder()
            .add("idUsuario", 1)
            .build();

    private static final JsonObject CATEGORIA_CREATE = Json.createObjectBuilder()
            .add("dsDescricao", "Teste Categoria")
            .add("dsCor", "#008000")
            .add("fgTipo", 0)
            .add("usuario", USUARIO_CADASTRO_CATEGORIA)
            .build();

    @Test
    void createCategoria() {
        String token = login.getToken();
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(CATEGORIA_CREATE.toString())
                .header("Authorization", token)
                .when()
                .post("/categoria")
                .then()
                .statusCode(200);
    }

    @Test
    void updateCategoria() {
    }

    @Test
    void findById() {
    }

    @Test
    void autoCompleteCategoria() {
    }

    @Test
    void listPaginacao() {
    }
}