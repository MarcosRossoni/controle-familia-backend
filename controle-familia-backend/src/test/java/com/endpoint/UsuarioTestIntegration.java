package com.endpoint;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.core.MediaType;
import org.h2.util.json.JSONObject;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@Tag("integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsuarioTestIntegration {

    static final JsonObject CIDADE = Json.createObjectBuilder()
            .add("idCidade", "1100015")
            .build();

    static final JsonObject NEW_USUARIO = Json.createObjectBuilder()
            .add("dsNome", "Teste Usuario")
            .add("dsEmail", "teste@teste.com.br")
            .add("dsTelefone", "46987654321")
            .add("dtNascimento", "1993-05-11")
            .add("dsCpf", "72629134091")
            .add("dsSenha", "maysienbfkshdfh")
            .add("dsEndereco", "Rua Teste")
            .add("numPredial", "1234")
            .add("dsBairro", "Bairro Teste")
            .add("dsComplemento", "teste")
            .add("cidade", CIDADE)
            .build();

    @Test
    @Order(1)
    void cadastrarUsuario() {

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(NEW_USUARIO.toString())
                .when().post("/usuario")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(2)
    void atualizacaoUsuario() {
    }

    @Test
    @Order(3)
    void listUsuarios() {
    }
}