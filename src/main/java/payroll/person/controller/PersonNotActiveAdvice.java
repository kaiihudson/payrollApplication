package payroll.person.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * The type Person not active advice.
 */
@RestControllerAdvice
class PersonNotActiveAdvice {
    /**
     * Person not active handler string.
     *
     * @param ex the ex
     * @return the string
     */
    @ExceptionHandler(PersonNotActiveException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    String PersonNotActiveHandler(PersonNotActiveException ex) {
        return ex.getMessage();
    }
}
