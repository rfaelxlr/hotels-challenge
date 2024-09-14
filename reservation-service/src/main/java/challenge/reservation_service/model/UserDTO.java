package challenge.reservation_service.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    @UserEmailUnique
    private String email;

    @NotNull
    @Size(max = 3)
    private String ddd;

    @NotNull
    @Size(max = 9)
    private String phoneNumber;

    @NotNull
    @UserExternalIdUnique
    private UUID externalId;

}
