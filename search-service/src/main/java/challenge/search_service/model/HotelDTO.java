package challenge.search_service.model;

import challenge.search_service.domain.Hotel;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Getter
@Setter
public class HotelDTO {

    private String name;

    private Double rating;

    private UUID externalId;

    private AddressDTO address;

    private Set<RoomDTO> rooms;

    public static HotelDTO mapFromEntity(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.name = hotel.getName();
        hotelDTO.rating = hotel.getRating();
        hotelDTO.externalId = hotel.getExternalId();
        hotelDTO.address = AddressDTO.mapFromEntity(hotel.getAddress());
        hotelDTO.rooms = hotel.getRooms().stream().map(RoomDTO::mapFromEntity).collect(Collectors.toSet());
        return hotelDTO;
    }
}
