package payroll.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import payroll.person.model.Person;

public interface PeopleRepository extends JpaRepository<Person, Long> {
	
}