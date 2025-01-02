package payroll.order.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import payroll.order.model.AppOrder;
import payroll.order.service.AppOrderDTO;
import payroll.order.service.OrderService;
import payroll.person.model.Person;
import payroll.person.service.PersonService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Order controller.
 */
@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderService orderService;
    private final PersonService personService;
    private final OrderDTOModelAssembler orderDTOModelAssembler;

    /**
     * Instantiates a new Order controller.
     *
     * @param orderService           the order service
     * @param personService          the person service
     * @param orderDTOModelAssembler the order dto model assembler
     */
    public OrderController(OrderService orderService, PersonService personService, OrderDTOModelAssembler orderDTOModelAssembler) {
        this.orderService = orderService;
        this.personService = personService;
        this.orderDTOModelAssembler = orderDTOModelAssembler;
    }

    /**
     * All collection model.
     *
     * @return the collection model
     */
    @GetMapping("/orders")
    public CollectionModel<EntityModel<AppOrderDTO>> all() {
        List<AppOrderDTO> order = orderService.getAllOrders();
        List<EntityModel<AppOrderDTO>> modelOrder = order.stream() //
                .map(orderDTOModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(modelOrder, linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    /**
     * One entity model.
     *
     * @param id the id
     * @return the entity model
     */
    @GetMapping("/order/{id}")
    public EntityModel<AppOrderDTO> one(@PathVariable("id") Long id) {
        AppOrderDTO order = orderService.getOrderById(id);
        return orderDTOModelAssembler.toModel(order);
    }

    /**
     * All by user collection model.
     *
     * @param id the id
     * @return the collection model
     */
    @GetMapping("/order")
    CollectionModel<EntityModel<AppOrderDTO>> allByUser(@RequestParam Long id) {
        List<AppOrderDTO> orderList = orderService //
                .getIncompleteOrderByUserId(id);
        List<EntityModel<AppOrderDTO>> modelOrderList = orderList.stream() //
                .map(orderDTOModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(modelOrderList, linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    /**
     * All by user where complete collection model.
     *
     * @param id the id
     * @return the collection model
     */
    @GetMapping("/complete_order")
    CollectionModel<EntityModel<AppOrderDTO>> allByUserWhereComplete(@RequestParam Long id) {
        List<AppOrderDTO> orderList = orderService //
                .getCompleteOrderByUserId(id);
        List<EntityModel<AppOrderDTO>> modelOrderList = orderList.stream() //
                .map(orderDTOModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(modelOrderList, linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    /**
     * New order response entity.
     *
     * @param newOrder the new order
     * @return the response entity
     */
    @PostMapping("/orders")
    ResponseEntity<?> newOrder(@RequestBody AppOrder newOrder) {
        Person person = personService.getPersonById(newOrder.getUserId());
        AppOrderDTO order = orderService
                .createOrder(newOrder, person);
        EntityModel<AppOrderDTO> entityModel = orderDTOModelAssembler //
                .toModel(order);
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/order/{id}")
    ResponseEntity<?> editOrder(@PathVariable Long id, @RequestBody AppOrder newOrder) {
        AppOrderDTO order = orderService.updateOrderById(id, newOrder);
        EntityModel<AppOrderDTO> entityModel = orderDTOModelAssembler
                .toModel(order);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

}
