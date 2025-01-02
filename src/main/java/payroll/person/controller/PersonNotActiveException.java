package payroll.person.controller;

/**
 * The type Person not active exception.
 */
public class PersonNotActiveException extends RuntimeException {
    /**
     * Instantiates a new Person not active exception.
     *
     * @param id the id
     */
    public PersonNotActiveException(Long id) {
        super("person with id " + id + " is already inactive");
    }
}
