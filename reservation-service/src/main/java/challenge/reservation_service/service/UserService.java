package challenge.reservation_service.service;

import challenge.reservation_service.domain.Reservation;
import challenge.reservation_service.domain.ReservationEvent;
import challenge.reservation_service.domain.User;
import challenge.reservation_service.model.UserDTO;
import challenge.reservation_service.repos.ReservationEventRepository;
import challenge.reservation_service.repos.ReservationRepository;
import challenge.reservation_service.repos.UserRepository;
import challenge.reservation_service.util.NotFoundException;
import challenge.reservation_service.util.ReferencedWarning;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationEventRepository reservationEventRepository;

    public UserService(final UserRepository userRepository,
            final ReservationRepository reservationRepository,
            final ReservationEventRepository reservationEventRepository) {
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        this.reservationEventRepository = reservationEventRepository;
    }

    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("id"));
        return users.stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .toList();
    }

    public UserDTO get(final Long id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getId();
    }

    public void update(final Long id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setDdd(user.getDdd());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setExternalId(user.getExternalId());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setDdd(userDTO.getDdd());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setExternalId(userDTO.getExternalId());
        return user;
    }

    public boolean emailExists(final String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    public boolean externalIdExists(final UUID externalId) {
        return userRepository.existsByExternalId(externalId);
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Reservation userReservation = reservationRepository.findFirstByUser(user);
        if (userReservation != null) {
            referencedWarning.setKey("user.reservation.user.referenced");
            referencedWarning.addParam(userReservation.getId());
            return referencedWarning;
        }
        final ReservationEvent userReservationEvent = reservationEventRepository.findFirstByUser(user);
        if (userReservationEvent != null) {
            referencedWarning.setKey("user.reservationEvent.user.referenced");
            referencedWarning.addParam(userReservationEvent.getId());
            return referencedWarning;
        }
        return null;
    }

}
