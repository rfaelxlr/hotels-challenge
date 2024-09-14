package challenge.search_service.model;

import challenge.search_service.domain.Room;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class RoomDTO {
    private Integer quantityAvailable;

    private Integer capacity;

    private BigDecimal price;

    private UUID externalId;

    public static RoomDTO mapFromEntity(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.quantityAvailable = room.getQuantityAvailable();
        roomDTO.capacity = room.getCapacity();
        roomDTO.price = room.getPrice();
        roomDTO.externalId = room.getExternalId();
        return roomDTO;
    }
}
