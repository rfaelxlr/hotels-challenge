package challenge.reservation_service.repos;

import challenge.reservation_service.domain.Reservation;
import challenge.reservation_service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation findFirstByUser(User user);

}
