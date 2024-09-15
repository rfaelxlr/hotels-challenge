package challenge.notification_service.service;

import challenge.notification_service.model.ReservationEventDTO;
import challenge.notification_service.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ListenerService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ListenerService.class);

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receiveMessage(String message) throws IOException {
        LOGGER.info(String.format("Received : %s", message));

        ReservationEventDTO reservation = Mapper.mapFromJson(message, ReservationEventDTO.class);

        notificationService.notify(reservation);
        notificationService.save(reservation);
    }

}
