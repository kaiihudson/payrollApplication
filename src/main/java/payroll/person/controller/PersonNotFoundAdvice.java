package payroll.person.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * The type Person not found advice.
 */
@RestControllerAdvice
class PersonNotFoundAdvice {
	/**
	 * Person not found handler string.
	 *
	 * @param ex the ex
	 * @return the string
	 */
	@ExceptionHandler(PersonNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String PersonNotFoundHandler(PersonNotFoundException ex) {
		return ex.getMessage();
	}
}
