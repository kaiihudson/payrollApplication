package payroll.person;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
class PersonNotFoundAdvice {
	@ExceptionHandler(PersonNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String PersonNotFoundHandler(PersonNotFoundException ex) {
		return ex.getMessage();
	}
}
