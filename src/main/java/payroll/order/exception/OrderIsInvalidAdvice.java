package payroll.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderIsInvalidAdvice {
    @ExceptionHandler(OrderIsInInvalidStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String OrderIsInvalidHandler(OrderIsInInvalidStateException ex) {return ex.getMessage();}
}
