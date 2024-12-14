package payroll.person;

public class PersonNotActiveException extends RuntimeException {
    public PersonNotActiveException(Long id) {
        super("person with id " + id + " is already inactive");
    }
}
