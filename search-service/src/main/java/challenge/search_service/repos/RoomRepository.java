package challenge.search_service.repos;

import challenge.search_service.domain.Hotel;
import challenge.search_service.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoomRepository extends JpaRepository<Room, Long> {

}
