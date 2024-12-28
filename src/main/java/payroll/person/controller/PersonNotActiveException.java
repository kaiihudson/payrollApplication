package payroll.person.controller;

public class PersonNotActiveException extends RuntimeException {
    public PersonNotActiveException(Long id) {
        super("person with id " + id + " is already inactive");
    }
}
