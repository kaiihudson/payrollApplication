package payroll.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import payroll.person.model.Person;
import payroll.person.model.Status;

import java.util.List;

public interface PeopleRepository extends JpaRepository<Person, Long> {
    List<Person> findPersonByStatus(Status status);
}