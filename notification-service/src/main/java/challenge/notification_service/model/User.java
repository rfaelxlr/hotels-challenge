package challenge.notification_service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class User {
    private String name;
    private String email;
    private String ddd;
    private String phoneNumber;
    private UUID externalId;
}
