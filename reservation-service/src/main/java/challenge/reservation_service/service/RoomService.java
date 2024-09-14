package challenge.reservation_service.service;

import challenge.reservation_service.domain.Room;
import challenge.reservation_service.repos.RoomRepository;
import challenge.reservation_service.util.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public Room getRoom(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow(() -> new NotFoundException("Room not found"));
    }

    public void updateRoomAvailability(Room room) {
        room.setQuantityAvailable(room.getQuantityAvailable() - 1);
        roomRepository.save(room);
    }
}
