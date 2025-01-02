package payroll.person.service;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import payroll.person.controller.PersonController;
import payroll.person.model.Person;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PersonMapper {
    PersonDTO mapToDTO(Person person) {
        return new PersonDTO.Builder()
                .setId(person.getId())
                .setName(person.getName())
                .setAddress(person.getAddress())
                .setPhoneNum(person.getPhoneNum())
                .build();
    }

    EntityModel<PersonDTO> mapToEntityModel(PersonDTO person) {
        return EntityModel.of(person,
                linkTo(methodOn(PersonController.class).one(person.getId())).withSelfRel()
                );
    }
}
