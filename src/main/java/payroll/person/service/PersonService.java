package payroll.person.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import payroll.order.model.AppOrder;
import payroll.order.model.OrderStatus;
import payroll.order.repository.OrderRepository;
import payroll.person.exception.PersonHasOutstandingOrdersException;
import payroll.person.exception.PersonNotActiveException;
import payroll.person.exception.PersonNotFoundException;
import payroll.person.model.PersonStatus;
import payroll.person.model.Person;
import payroll.person.repository.PeopleRepository;

@Service
public class PersonService {
	
	private final PeopleRepository peopleRepository;
	private final PersonMapper personMapper;
	private final OrderRepository orderRepository;

	PersonService(PeopleRepository peopleRepository, PersonMapper personMapper, OrderRepository orderRepository){
		this.peopleRepository = peopleRepository;
		this.personMapper = personMapper;
		this.orderRepository = orderRepository;
	}

	public Page<EntityModel<PersonDTO>> getAllPeople(Pageable pageable){
		Page<Person> pageOfPerson = peopleRepository.findPersonByStatus(PersonStatus.ACTIVE, pageable);
        return new PageImpl<>(pageOfPerson
				.stream()
				.map(personMapper::mapToDTO)
				.map(personMapper::mapToEntityModel)
				.collect(Collectors.toList()),
				pageable,
				pageOfPerson.getTotalElements());
	}

	public PersonDTO createPerson(Person person) {
		return personMapper.mapToDTO(peopleRepository.save(person));
	}

	public PersonDTO getPersonDTOById(Long id) {
		Optional<Person> person = peopleRepository.findById(id);
		if (person.isPresent()){
			return personMapper.mapToDTO(person.get());
		} else {
			throw new PersonNotFoundException(id);
		}
	}

	public Person getPersonById(Long id) {
		Optional<Person> person = peopleRepository.findById(id);
		if (person.isPresent()) {
			return person.get();
		} else {
			throw new PersonNotFoundException(id);
		}
	}

	public void deletePersonByID(Long id) {
		Person person = peopleRepository.findById(id) //
				.orElseThrow(() -> new PersonNotFoundException(id));

		Collection<OrderStatus> arr = new ArrayList<>();
		arr.add(OrderStatus.COMPLETE);
		arr.add(OrderStatus.ABORTED);
		Optional<List<AppOrder>> order = orderRepository.findByBindUserAndOrderStatusNotIn(person, arr);
		if (order.isPresent()){
			if (person.getStatus() == PersonStatus.ACTIVE){
				person.setStatus(PersonStatus.INACTIVE);
				peopleRepository.save(person);
			} else {
				throw(new PersonNotActiveException(id));
			}
		} else {
			throw(new PersonHasOutstandingOrdersException(id));
		}

	}

	public PersonDTO replacePersonById(Long id, Person newPerson) {
		Optional<Person> originalPerson = peopleRepository.findById(id);
		if (originalPerson.isPresent()){
			originalPerson.map(person -> {
				person.setName(newPerson.getName());
				person.setIdentity(newPerson.getIdentity());
				person.setPhoneNum(newPerson.getPhoneNum());
				return peopleRepository.save(person);
			});
		} else {
			throw new PersonNotFoundException(id);
		}
        return null;
    }
}
