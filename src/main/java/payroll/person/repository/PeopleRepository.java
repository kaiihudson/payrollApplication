package payroll.person.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import payroll.person.model.Person;
import payroll.person.model.PersonStatus;

public interface PeopleRepository extends JpaRepository<Person, Long> {

    Page<Person> findPersonByStatus(PersonStatus personStatus, Pageable pageable);
}