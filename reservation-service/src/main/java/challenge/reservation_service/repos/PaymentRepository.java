package challenge.reservation_service.repos;

import challenge.reservation_service.domain.Payment;
import challenge.reservation_service.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findFirstByReservation(Reservation reservation);

}
