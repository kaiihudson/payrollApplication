package payroll.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import payroll.person.model.Person;
import payroll.person.model.Status;

import java.util.List;

/**
 * The interface People repository.
 */
public interface PeopleRepository extends JpaRepository<Person, Long> {
    /**
     * Find person by status list.
     *
     * @param status the status
     * @return the list
     */
    List<Person> findPersonByStatus(Status status);
}