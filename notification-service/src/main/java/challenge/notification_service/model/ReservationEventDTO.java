package challenge.notification_service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
enum ReservationStatus {

    PENDING,
    CONFIRMED,
    CHECKED_IN,
    CHECKED_OUT,
    CANCELLED
}

@Getter
@Setter
public class ReservationEventDTO {
    private UUID externalId;
    private ReservationStatus status;
    private Reservation reservation;
}

