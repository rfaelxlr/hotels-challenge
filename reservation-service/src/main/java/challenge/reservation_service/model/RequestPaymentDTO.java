package challenge.reservation_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RequestPaymentDTO {
    @NotEmpty(message = "type cannot be empty")
    private PaymentType type;

    @NotNull(message = "value cannot be null")
    @Digits(integer = 10, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "51.08")
    private BigDecimal value;

    @NotNull(message = "installmentNumber cannot be null")
    private Integer installmentNumber;
}
