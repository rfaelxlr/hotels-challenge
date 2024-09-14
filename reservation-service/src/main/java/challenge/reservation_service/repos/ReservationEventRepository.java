package challenge.reservation_service.repos;

import challenge.reservation_service.domain.Reservation;
import challenge.reservation_service.domain.ReservationEvent;
import challenge.reservation_service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservationEventRepository extends JpaRepository<ReservationEvent, Long> {

    ReservationEvent findFirstByUser(User user);

    ReservationEvent findFirstByReservation(Reservation reservation);

}
