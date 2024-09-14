package challenge.reservation_service.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class BussinessException extends RuntimeException {

    public BussinessException() {
        super();
    }

    public BussinessException(final String message) {
        super(message);
    }

}
