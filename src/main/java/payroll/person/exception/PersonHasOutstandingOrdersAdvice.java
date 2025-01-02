package payroll.person.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PersonHasOutstandingOrdersAdvice {
    @ExceptionHandler(PersonHasOutstandingOrdersException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String PersonHasOutstandingOrdersHandler(PersonHasOutstandingOrdersException ex) {return ex.getMessage();}
}
