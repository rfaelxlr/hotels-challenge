package challenge.reservation_service.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestReservationDTO {

    @NotNull(message = "checkInDate cannot be null")
    private LocalDate checkInDate;

    @NotNull(message = "checkOutDate cannot be null")
    private LocalDate checkOutDate;

    @NotNull(message = "guestsNumber cannot be null")
    private Integer guestsNumber;

    @NotNull(message = "userId cannot be null")
    private Long userId;

    @NotNull(message = "roomId cannot be null")
    private Long roomId;

    @NotNull(message = "payments cannot be null")
    private List<RequestPaymentDTO> payments;
}
