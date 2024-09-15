package challenge.search_service.repos;

import challenge.search_service.domain.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query(value = "SELECT DISTINCT h.* FROM hotels h " +
            "JOIN addresses a ON h.address_id = a.id " +
            "JOIN rooms r ON h.id = r.hotel_id " +
            "WHERE (:state IS NULL OR a.state = :state) " +
            "AND (:city IS NULL OR a.city = :city) " +
            "AND (:numbersOfRooms IS NULL OR r.quantity_available = :numbersOfRooms) " +
            "AND (:roomCapacity IS NULL OR r.capacity >= :roomCapacity)",
            nativeQuery = true)
    Page<Hotel> search(String state,
                       String city,
                       Integer numbersOfRooms,
                       Integer roomCapacity,
                       Pageable pageRequest);
}
