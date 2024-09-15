package challenge.notification_service.service;

import challenge.notification_service.domain.Notification;
import challenge.notification_service.model.NotificationType;
import challenge.notification_service.model.ReservationEventDTO;
import challenge.notification_service.repos.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class NotificationService {
    public static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationRepository notificationRepository;


    public void notify(ReservationEventDTO reservationEventDTO) {
        LOGGER.info("Send to email: status: {} , checkinDate: {} , checkoutDate: {} , userName: {}",
                reservationEventDTO.getStatus(),
                reservationEventDTO.getReservation().getCheckInDate(),
                reservationEventDTO.getReservation().getCheckOutDate().toString(),
                reservationEventDTO.getReservation().getUser().getName());

    }

    public void save(ReservationEventDTO reservation) {
        notificationRepository.save(Notification.builder()
                .reservationEventId(reservation.getExternalId())
                .type(NotificationType.EMAIL)
                .isProcessed(true)
                .processedDate(LocalDateTime.now())
                .build());
    }
}
