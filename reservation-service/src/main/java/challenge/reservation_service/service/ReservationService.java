package challenge.reservation_service.service;

import challenge.reservation_service.domain.Payment;
import challenge.reservation_service.domain.Reservation;
import challenge.reservation_service.domain.ReservationEvent;
import challenge.reservation_service.domain.Room;
import challenge.reservation_service.domain.User;
import challenge.reservation_service.model.ReservationDTO;
import challenge.reservation_service.repos.PaymentRepository;
import challenge.reservation_service.repos.ReservationEventRepository;
import challenge.reservation_service.repos.ReservationRepository;
import challenge.reservation_service.repos.RoomRepository;
import challenge.reservation_service.repos.UserRepository;
import challenge.reservation_service.util.NotFoundException;
import challenge.reservation_service.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservationEventRepository reservationEventRepository;
    private final PaymentRepository paymentRepository;

    public ReservationService(final ReservationRepository reservationRepository,
            final UserRepository userRepository, final RoomRepository roomRepository,
            final ReservationEventRepository reservationEventRepository,
            final PaymentRepository paymentRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.reservationEventRepository = reservationEventRepository;
        this.paymentRepository = paymentRepository;
    }

    public List<ReservationDTO> findAll() {
        final List<Reservation> reservations = reservationRepository.findAll(Sort.by("id"));
        return reservations.stream()
                .map(reservation -> mapToDTO(reservation, new ReservationDTO()))
                .toList();
    }

    public ReservationDTO get(final Long id) {
        return reservationRepository.findById(id)
                .map(reservation -> mapToDTO(reservation, new ReservationDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ReservationDTO reservationDTO) {
        final Reservation reservation = new Reservation();
        mapToEntity(reservationDTO, reservation);
        return reservationRepository.save(reservation).getId();
    }

    public void update(final Long id, final ReservationDTO reservationDTO) {
        final Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(reservationDTO, reservation);
        reservationRepository.save(reservation);
    }

    public void delete(final Long id) {
        reservationRepository.deleteById(id);
    }

    private ReservationDTO mapToDTO(final Reservation reservation,
            final ReservationDTO reservationDTO) {
        reservationDTO.setId(reservation.getId());
        reservationDTO.setStatus(reservation.getStatus());
        reservationDTO.setCheckInDate(reservation.getCheckInDate());
        reservationDTO.setCheckOutDate(reservation.getCheckOutDate());
        reservationDTO.setGuestsNumber(reservation.getGuestsNumber());
        reservationDTO.setTotalPrice(reservation.getTotalPrice());
        reservationDTO.setUser(reservation.getUser() == null ? null : reservation.getUser().getId());
        reservationDTO.setRoom(reservation.getRoom() == null ? null : reservation.getRoom().getId());
        return reservationDTO;
    }

    private Reservation mapToEntity(final ReservationDTO reservationDTO,
            final Reservation reservation) {
        reservation.setStatus(reservationDTO.getStatus());
        reservation.setCheckInDate(reservationDTO.getCheckInDate());
        reservation.setCheckOutDate(reservationDTO.getCheckOutDate());
        reservation.setGuestsNumber(reservationDTO.getGuestsNumber());
        reservation.setTotalPrice(reservationDTO.getTotalPrice());
        final User user = reservationDTO.getUser() == null ? null : userRepository.findById(reservationDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        reservation.setUser(user);
        final Room room = reservationDTO.getRoom() == null ? null : roomRepository.findById(reservationDTO.getRoom())
                .orElseThrow(() -> new NotFoundException("room not found"));
        reservation.setRoom(room);
        return reservation;
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final ReservationEvent reservationReservationEvent = reservationEventRepository.findFirstByReservation(reservation);
        if (reservationReservationEvent != null) {
            referencedWarning.setKey("reservation.reservationEvent.reservation.referenced");
            referencedWarning.addParam(reservationReservationEvent.getId());
            return referencedWarning;
        }
        final Payment reservationPayment = paymentRepository.findFirstByReservation(reservation);
        if (reservationPayment != null) {
            referencedWarning.setKey("reservation.payment.reservation.referenced");
            referencedWarning.addParam(reservationPayment.getId());
            return referencedWarning;
        }
        return null;
    }

}
