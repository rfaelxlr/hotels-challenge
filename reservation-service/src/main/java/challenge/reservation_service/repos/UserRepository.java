package challenge.reservation_service.repos;

import challenge.reservation_service.domain.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByExternalId(UUID externalId);

}
