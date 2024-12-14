package payroll.person;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
class PersonNotActiveAdvice {
    @ExceptionHandler(PersonNotActiveException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    String PersonNotActiveHandler(PersonNotActiveException ex) {
        return ex.getMessage();
    }
}
