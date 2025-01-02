package payroll.person.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import payroll.person.service.PersonService;
import payroll.person.model.Status;
import payroll.person.model.Person;

/**
 * The type Person controller.
 */
@RestController
@RequestMapping("/api/v1")
public class PersonController {
    private final PersonService service;
    private final PersonModelAssembler assembler;

    /**
     * Instantiates a new Person controller.
     *
     * @param service   the service
     * @param assembler the assembler
     */
    PersonController(PersonService service, PersonModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    /**
     * All collection model.
     *
     * @return the collection model
     */
    @GetMapping("/people")
    public CollectionModel<EntityModel<Person>> all() {
        List<Person> person = service.getAllPeople();
        List<EntityModel<Person>> modelPerson = person.stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(modelPerson, linkTo(methodOn(PersonController.class).all()).withSelfRel());
    }

    /**
     * New person response entity.
     *
     * @param newPerson the new person
     * @return the response entity
     */
    @PostMapping("/people")
    ResponseEntity<?> newPerson(@RequestBody Person newPerson) {
        newPerson.setStatus(Status.ACTIVE);
        EntityModel<Person> entityModel = assembler.toModel(service.createPerson(newPerson));
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    /**
     * One entity model.
     *
     * @param id the id
     * @return the entity model
     */
    @GetMapping("/person/{id}")
    public EntityModel<Person> one(@PathVariable("id") Long id) {
        Person person = service.getPersonById(id);
        return assembler.toModel(person);
    }

    /**
     * Replace person response entity.
     *
     * @param newPerson the new person
     * @param id        the id
     * @return the response entity
     */
    @PutMapping("/person/{id}")
    ResponseEntity<?> replacePerson(@RequestBody Person newPerson, @PathVariable("id") Long id) {
        Person updatedPerson = service.replacePersonById(id, newPerson);
        EntityModel<Person> entityModel = assembler.toModel(updatedPerson);
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())//
                .body(entityModel);
    }

    /**
     * Delete person response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/person/{id}")
    ResponseEntity<?> deletePerson(@PathVariable("id") Long id) {
        service.deletePersonByID(id);
        return ResponseEntity.noContent().build();
    }
}