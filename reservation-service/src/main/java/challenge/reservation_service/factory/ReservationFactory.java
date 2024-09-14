package challenge.reservation_service.factory;

import challenge.reservation_service.domain.Reservation;
import challenge.reservation_service.domain.Room;
import challenge.reservation_service.domain.User;
import challenge.reservation_service.model.RequestPaymentDTO;
import challenge.reservation_service.model.RequestReservationDTO;
import challenge.reservation_service.model.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ReservationFactory {
    private final PaymentFactory paymentFactory;

    public Reservation create(RequestReservationDTO requestReservationDTO, User user, Room room) {
        return Reservation.builder()
                .checkInDate(requestReservationDTO.getCheckInDate())
                .checkOutDate(requestReservationDTO.getCheckOutDate())
                .guestsNumber(requestReservationDTO.getGuestsNumber())
                .status(ReservationStatus.PENDING)
                .user(user)
                .room(room)
                .payments(paymentFactory.create(requestReservationDTO.getPayments()))
                .totalPrice(requestReservationDTO.getPayments().stream()
                        .map(RequestPaymentDTO::getValue)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .build();
    }
}
