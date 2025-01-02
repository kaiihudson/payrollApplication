package payroll.person.controller;

/**
 * The type Person not found exception.
 */
public class PersonNotFoundException extends RuntimeException {
	/**
	 * Instantiates a new Person not found exception.
	 *
	 * @param id the id
	 */
	public PersonNotFoundException(Long id) {
		super("Cannot find person with id " + id);
	}
}
