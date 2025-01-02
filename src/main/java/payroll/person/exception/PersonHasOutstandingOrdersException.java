package payroll.person.exception;

public class PersonHasOutstandingOrdersException extends RuntimeException {
    public PersonHasOutstandingOrdersException(Long id) {
        super("User with id: " + id + " has pending orders");
    }
}
