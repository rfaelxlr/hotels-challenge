package challenge.reservation_service;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableRabbit
public class ReservationServiceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ReservationServiceApplication.class, args);
    }

}
