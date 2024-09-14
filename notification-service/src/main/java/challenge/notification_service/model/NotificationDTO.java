package challenge.notification_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NotificationDTO {

    private Long id;

    @NotNull
    @JsonProperty("isProcessed")
    private Boolean isProcessed;

    @NotNull
    private LocalDateTime processedDate;

    @NotNull
    private UUID reservationEventId;

    @NotNull
    private NotificationType type;

}
