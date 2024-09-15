package challenge.reservation_service.repos;

import challenge.reservation_service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
