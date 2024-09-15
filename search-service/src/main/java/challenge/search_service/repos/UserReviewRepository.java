package challenge.search_service.repos;

import challenge.search_service.domain.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserReviewRepository extends JpaRepository<UserReview, Long> {

}
