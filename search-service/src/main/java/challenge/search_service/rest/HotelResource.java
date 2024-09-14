package challenge.search_service.rest;

import challenge.search_service.domain.Hotel;
import challenge.search_service.model.HotelDTO;
import challenge.search_service.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/v1/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class HotelResource {

    private final HotelService hotelService;

    @GetMapping
    public ResponseEntity<PageImpl<HotelDTO>> getAllHotels(@RequestParam(required = false) String state,
                                                           @RequestParam(required = false) String city,
                                                           @RequestParam(required = false) Integer numbersOfRooms,
                                                           @RequestParam(required = false) Integer roomCapacity,
                                                           @RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "5") Integer size
    ) {
        return ResponseEntity.ok(hotelService.search(state, city, numbersOfRooms, roomCapacity, page, size));
    }
}
