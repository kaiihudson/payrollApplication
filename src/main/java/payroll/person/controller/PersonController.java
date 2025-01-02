package payroll.person.controller;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import payroll.person.service.PersonDTO;
import payroll.person.service.PersonService;
import payroll.person.model.PersonStatus;
import payroll.person.model.Person;

@RestController
@RequestMapping("/api/v1")
public class PersonController {
    private final PersonService service;
    private final PersonModelAssembler assembler;

    PersonController(PersonService service, PersonModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }


    @GetMapping("/people")
    public HttpEntity<PagedModel<Person>> all(@PageableDefault(size = 15, sort = "id") Pageable p, PagedResourcesAssembler pagedAssembler) {
        Page<EntityModel<PersonDTO>> people = service.getAllPeople(p);
        return ResponseEntity.ok(pagedAssembler.toModel(people));
    }

    @PostMapping("/people")
    ResponseEntity<?> newPerson(@RequestBody Person newPerson) {
        newPerson.setStatus(PersonStatus.ACTIVE);
        EntityModel<PersonDTO> entityModel = assembler.toModel(service.createPerson(newPerson));
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @GetMapping("/person/{id}")
    public EntityModel<PersonDTO> one(@PathVariable("id") Long id) {
        PersonDTO person = service.getPersonDTOById(id);
        return assembler.toModel(person);
    }
    @PutMapping("/person/{id}")
    ResponseEntity<?> replacePerson(@RequestBody Person newPerson, @PathVariable("id") Long id) {
        PersonDTO updatedPerson = service.replacePersonById(id, newPerson);
        EntityModel<PersonDTO> entityModel = assembler.toModel(updatedPerson);
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(entityModel);
    }

    @DeleteMapping("/person/{id}")
    ResponseEntity<?> deletePerson(@PathVariable("id") Long id) {
        service.deletePersonByID(id);
        return ResponseEntity.noContent().build();
    }
}