package payroll.person.service;

import java.util.List;

import org.springframework.stereotype.Service;
import payroll.person.PersonNotActiveException;
import payroll.person.PersonNotFoundException;
import payroll.person.model.Status;
import payroll.person.model.Person;
import payroll.person.repository.PeopleRepository;

@Service
public class PersonService {
	
	private final PeopleRepository peopleRepository;
	
	PersonService(PeopleRepository peopleRepository){
		this.peopleRepository = peopleRepository;
	}
	
	public List<Person> getAllPeople(){
		List<Person> personList = peopleRepository.findAll();
		personList.removeIf(person -> person.getStatus() == Status.INACTIVE);
		return personList;
	}
	public Person createPerson(Person person) {
		return peopleRepository.save(person);
	}
	
	public Person getPersonById(Long id) {
		return peopleRepository.findById(id) //
				.orElseThrow(() -> new PersonNotFoundException(id));
	}
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
