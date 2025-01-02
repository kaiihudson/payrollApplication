package payroll.person.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import payroll.person.model.Person;
import payroll.person.service.PersonDTO;

@Component
public class PersonModelAssembler implements RepresentationModelAssembler<PersonDTO, EntityModel<PersonDTO>> {

	@Override
	public EntityModel<PersonDTO> toModel(PersonDTO person){
		return EntityModel.of(person, //
				linkTo(methodOn(PersonController.class).one(person.getId())).withSelfRel()
				);
	}
}
