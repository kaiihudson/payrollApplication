package payroll.person.exception;

public class PersonNotFoundException extends RuntimeException {
	public PersonNotFoundException(Long id) {
		super("Cannot find person with id " + id);
	}
}
