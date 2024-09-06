package com.endpoint;

import com.login.Login;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@Tag("integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoriaEndpointTest {

    @Inject
    Login login;

    @Test
    void createCategoria() {
        String token = login.getToken();
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