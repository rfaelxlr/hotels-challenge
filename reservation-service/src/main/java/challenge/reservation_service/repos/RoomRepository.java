package challenge.reservation_service.repos;

import challenge.reservation_service.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoomRepository extends JpaRepository<Room, Long> {
}
