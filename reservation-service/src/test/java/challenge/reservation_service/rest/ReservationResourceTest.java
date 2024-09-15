package challenge.reservation_service.rest;

import challenge.reservation_service.model.PaymentType;
import challenge.reservation_service.model.RequestPaymentDTO;
import challenge.reservation_service.model.RequestReservationDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationResourceTest {
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );
    @LocalServerPort
    private Integer port;

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
    void shouldCreateReservation() {
        RequestReservationDTO request = buildRequest();
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(request)
                .post("/v1/reservations")
                .then()
                .statusCode(201);
    }

    @Test
    void shouldReturn422WhenCheckInDateAfterCheckOutDate() {
        RequestReservationDTO request = buildRequestWithCheckInDateAfterCheckOutDate();
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(request)
                .post("/v1/reservations")
                .then()
                .statusCode(422)
                .body("message", equalTo("checkInDate cannot be after checkOutDate"));
    }

    private RequestReservationDTO buildRequest() {
        return RequestReservationDTO.builder()
                .checkInDate(LocalDate.of(2024, 9, 14))
                .checkOutDate(LocalDate.of(2024, 9, 17))
                .guestsNumber(4)
                .userId(1L)
                .roomId(10002L)
                .payments(List.of(RequestPaymentDTO.builder()
                        .type(PaymentType.PIX)
                        .installmentNumber(1)
                        .value(BigDecimal.valueOf(298.74))
                        .build()))
                .build();
    }

    private RequestReservationDTO buildRequestWithCheckInDateAfterCheckOutDate() {
        return RequestReservationDTO.builder()
                .checkInDate(LocalDate.of(2024, 9, 19))
                .checkOutDate(LocalDate.of(2024, 9, 17))
                .guestsNumber(4)
                .userId(1L)
                .roomId(10002L)
                .payments(List.of(RequestPaymentDTO.builder()
                        .type(PaymentType.PIX)
                        .installmentNumber(1)
                        .value(BigDecimal.valueOf(298.74))
                        .build()))
                .build();
    }
}
