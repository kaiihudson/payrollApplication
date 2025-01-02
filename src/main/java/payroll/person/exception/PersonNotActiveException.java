package payroll.person.exception;

public class PersonNotActiveException extends RuntimeException {
    public PersonNotActiveException(Long id) {
        super("person with id " + id + " is already inactive");
    }
}
