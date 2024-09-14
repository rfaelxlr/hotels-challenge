package challenge.search_service.service;

import challenge.search_service.domain.Hotel;
import challenge.search_service.model.HotelDTO;
import challenge.search_service.repos.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public PageImpl<HotelDTO> search(String state, String city, Integer numbersOfRooms, Integer roomCapacity, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Hotel> hotels = hotelRepository.search(state,
                city,
                numbersOfRooms,
                roomCapacity,
                pageRequest);

        List<HotelDTO> hotelDTOs = hotels.getContent().stream().map(HotelDTO::mapFromEntity).toList();

        return new PageImpl<>(hotelDTOs, pageRequest, hotels.getTotalElements());
    }
}
