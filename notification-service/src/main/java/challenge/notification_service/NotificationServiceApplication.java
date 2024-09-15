package challenge.notification_service;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableRabbit
public class NotificationServiceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

}
