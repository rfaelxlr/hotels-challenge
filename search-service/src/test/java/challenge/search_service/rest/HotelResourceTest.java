package challenge.search_service.rest;

import challenge.search_service.repos.HotelRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HotelResourceTest {
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );
    @LocalServerPort
    private Integer port;
    @Autowired
    private HotelRepository hotelRepository;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        //poderia deletar tudo que tem no banco e incluir o que quero
        //mas como estou usando scripts que populam as minhas tabelas via flyway, vou deixar do jeito que está
    }

    @Test
    void shouldReturnPaginatedHotels() {
        given()
                .contentType(ContentType.JSON)
                .queryParam("page", 0)
                .queryParam("size", 5)
                .when()
                .get("/v1/hotels")
                .then()
                .statusCode(200)
                .body("page.size", equalTo(5))
                .body("page.totalElements", equalTo(500))
                .body("page.totalPages", equalTo(100));
    }

    @Test
    void shouldReturnPaginatedHotelsWhenStateParamIsRS() {
        given()
                .contentType(ContentType.JSON)
                .queryParam("page", 0)
                .queryParam("size", 5)
                .queryParam("state", "RS")
                .when()
                .get("/v1/hotels")
                .then()
                .statusCode(200)
                .body("page.size", equalTo(5))
                .body("page.totalElements", equalTo(40))
                .body("page.totalPages", equalTo(8));
    }

    @Test
    void shouldReturnPaginatedHotelsWhenCityParamIsPortoAlegre() {
        given()
                .contentType(ContentType.JSON)
                .queryParam("page", 0)
                .queryParam("size", 5)
                .queryParam("city", "Porto Alegre")
                .when()
                .get("/v1/hotels")
                .then()
                .statusCode(200)
                .body("page.size", equalTo(5))
                .body("page.totalElements", equalTo(40))
                .body("page.totalPages", equalTo(8));
    }

    @Test
    void shouldReturnPaginatedHotelsWhenNumbersOfRoomsIs20() {
        given()
                .contentType(ContentType.JSON)
                .queryParam("page", 0)
                .queryParam("size", 5)
                .queryParam("numbersOfRooms", 20)
                .when()
                .get("/v1/hotels")
                .then()
                .statusCode(200)
                .body("page.size", equalTo(5))
                .body("page.totalElements", equalTo(79))
                .body("page.totalPages", equalTo(16));
    }

    @Test
    void shouldReturnPaginatedHotelsWhenRoomCapacityIs4() {
        given()
                .contentType(ContentType.JSON)
                .queryParam("page", 0)
                .queryParam("size", 5)
                .queryParam("roomCapacity", 4)
                .when()
                .get("/v1/hotels")
                .then()
                .statusCode(200)
                .body("page.size", equalTo(5))
                .body("page.totalElements", equalTo(308))
                .body("page.totalPages", equalTo(62));
    }

    @Test
    void shouldReturnPaginatedHotelsWhenRoomCapacityIs4AndStateIsRS() {
        given()
                .contentType(ContentType.JSON)
                .queryParam("page", 0)
                .queryParam("size", 5)
                .queryParam("roomCapacity", 4)
                .queryParam("state", "RS")
                .when()
                .get("/v1/hotels")
                .then()
                .statusCode(200)
                .body("page.size", equalTo(5))
                .body("page.totalElements", equalTo(24))
                .body("page.totalPages", equalTo(5));
    }
}
