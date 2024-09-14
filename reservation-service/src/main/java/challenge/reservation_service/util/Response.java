package challenge.reservation_service.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response {
    private Integer code;
    private String message;
    private String description;
    private String date;
    private String helpUrl;
}
