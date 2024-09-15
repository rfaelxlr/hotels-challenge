package challenge.reservation_service.service;

import challenge.reservation_service.domain.Reservation;
import challenge.reservation_service.domain.ReservationEvent;
import challenge.reservation_service.repos.ReservationEventRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
    private final RabbitTemplate rabbitTemplate;
    private final ReservationEventRepository reservationEventRepository;
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.routing-key}")
    private String routingKey;

    public void notify(final Reservation reservation) {
        try {
            ReservationEvent event = ReservationEvent.builder()
                    .status(reservation.getStatus())
                    .reservation(reservation)
                    .user(reservation.getUser())
                    .externalId(UUID.randomUUID())
                    .build();

            rabbitTemplate.convertAndSend(exchange, routingKey, event);
            reservationEventRepository.save(event);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
