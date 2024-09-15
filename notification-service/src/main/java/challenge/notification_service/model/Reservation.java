package challenge.notification_service.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class Reservation {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer guestsNumber;
    private BigDecimal totalPrice;
    private User user;
}
