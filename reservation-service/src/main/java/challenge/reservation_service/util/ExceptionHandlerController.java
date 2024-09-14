package challenge.reservation_service.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BussinessException.class)
    public ResponseEntity<Response> validation(BussinessException e, HttpServletRequest request) {

        Response resp = Response.builder()
                .code(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .date(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                .message(e.getMessage())
                .helpUrl(request.getRequestURI())
                .description("Violated request data integrity")
                .build();

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(resp);
    }

}
