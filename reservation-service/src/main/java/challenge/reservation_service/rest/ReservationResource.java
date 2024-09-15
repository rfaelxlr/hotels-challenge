package challenge.reservation_service.rest;

import challenge.reservation_service.model.RequestReservationDTO;
import challenge.reservation_service.service.ReservationService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/v1/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ReservationResource {

    private final ReservationService reservationService;

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createReservation(
            @RequestBody @Valid final RequestReservationDTO requestReservationDTO) {
        final Long createdId = reservationService.create(requestReservationDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

}
