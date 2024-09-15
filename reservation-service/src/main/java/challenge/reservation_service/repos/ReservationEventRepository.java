package challenge.reservation_service.repos;

import challenge.reservation_service.domain.ReservationEvent;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservationEventRepository extends JpaRepository<ReservationEvent, Long> {
}
