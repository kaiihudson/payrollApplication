package payroll.person.service;

import java.util.List;

import org.springframework.stereotype.Service;
import payroll.person.controller.PersonNotActiveException;
import payroll.person.controller.PersonNotFoundException;
import payroll.person.model.Status;
import payroll.person.model.Person;
import payroll.person.repository.PeopleRepository;

/**
 * The type Person service.
 */
@Service
public class PersonService {
	
	private final PeopleRepository peopleRepository;

	/**
	 * Instantiates a new Person service.
	 *
	 * @param peopleRepository the people repository
	 */
	PersonService(PeopleRepository peopleRepository){
		this.peopleRepository = peopleRepository;
	}

	/**
	 * Get all people list.
	 *
	 * @return the list
	 */
	public List<Person> getAllPeople(){
        return peopleRepository.findPersonByStatus(Status.ACTIVE);
	}

	/**
	 * Create person.
	 *
	 * @param person the person
	 * @return the person
	 */
	public Person createPerson(Person person) {
		return peopleRepository.save(person);
	}

	/**
	 * Gets person by id.
	 *
	 * @param id the id
	 * @return the person by id
	 */
	public Person getPersonById(Long id) {
		return peopleRepository.findById(id) //
				.orElseThrow(() -> new PersonNotFoundException(id));
	}

	/**
	 * Delete person by id.
	 *
	 * @param id the id
	 */
	public void deletePersonByID(Long id) {
		Person person = peopleRepository.findById(id) //
				.orElseThrow(() -> new PersonNotFoundException(id));
		if (person.getStatus() == Status.ACTIVE){
			person.setStatus(Status.INACTIVE);
			peopleRepository.save(person);
		} else {
			throw(new PersonNotActiveException(id));
		}
	}

	/**
	 * Replace person by id person.
	 *
	 * @param id        the id
	 * @param newPerson the new person
	 * @return the person
	 */
	public Person replacePersonById(Long id, Person newPerson) {
		return peopleRepository.findById(id) //
				.map(person -> {
					person.setName(newPerson.getName());
					person.setIdentity(newPerson.getIdentity());
					person.setPhoneNum(newPerson.getPhoneNum());
					return peopleRepository.save(person);
				}) //
				.orElseGet(()-> {
					return peopleRepository.save(newPerson);
				});
	}
}
