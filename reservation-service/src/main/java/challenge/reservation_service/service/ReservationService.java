package challenge.reservation_service.service;

import challenge.reservation_service.domain.Reservation;
import challenge.reservation_service.domain.Room;
import challenge.reservation_service.domain.User;
import challenge.reservation_service.factory.ReservationFactory;
import challenge.reservation_service.model.RequestReservationDTO;
import challenge.reservation_service.repos.ReservationRepository;
import challenge.reservation_service.util.BussinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationFactory reservationFactory;
    private final UserService userService;
    private final RoomService roomService;
    private final NotificationService notificationService;

    @Transactional
    public Long create(final RequestReservationDTO requestReservationDTO) {
        final User user = userService.get(requestReservationDTO.getUserId());
        final Room room = roomService.getRoom(requestReservationDTO.getRoomId());

        final Reservation reservation = reservationFactory.create(requestReservationDTO, user, room);

        validateReserve(reservation, room);

        reservationRepository.save(reservation);
        roomService.updateRoomAvailability(room);
        notificationService.notify(reservation);
        return reservation.getId();
    }

    private void validateReserve(Reservation reservation, Room room) {
        if (reservation.getCheckInDate().isAfter(reservation.getCheckOutDate())) {
            throw new BussinessException("checkInDate cannot be after checkOutDate");
        }

        if (reservation.getCheckInDate().isEqual(reservation.getCheckOutDate())) {
            throw new BussinessException("checkInDate cannot be equal to checkOutDate");
        }

        if (room.getCapacity() < reservation.getGuestsNumber()) {
            throw new BussinessException("guestsNumber cannot be greater than room capacity");
        }

        if (room.getQuantityAvailable() < 1) {
            throw new BussinessException("room is not available");
        }

        if (!room.getPrice().equals(reservation.getTotalPrice())) {
            throw new BussinessException("total price is not equal to room price");
        }
    }

}
