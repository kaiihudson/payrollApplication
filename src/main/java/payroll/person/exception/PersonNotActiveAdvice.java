package payroll.person.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
class PersonNotActiveAdvice {
    @ExceptionHandler(PersonNotActiveException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String PersonNotActiveHandler(PersonNotActiveException ex) {
        return ex.getMessage();
    }
}
