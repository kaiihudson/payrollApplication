package payroll.order.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import payroll.order.model.AppOrder;
import payroll.order.model.OrderStatus;
import payroll.order.service.AppOrderDTO;
import payroll.order.service.AppOrderMapper;
import payroll.order.service.OrderService;
import payroll.person.model.Person;
import payroll.person.service.PersonService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderService orderService;
    private final PersonService personService;
    private final OrderDTOModelAssembler orderDTOModelAssembler;

    public OrderController(OrderService orderService, PersonService personService, OrderDTOModelAssembler orderDTOModelAssembler) {
        this.orderService = orderService;
        this.personService = personService;
        this.orderDTOModelAssembler = orderDTOModelAssembler;
    }

    @GetMapping("/orders")
    public CollectionModel<EntityModel<AppOrderDTO>> all() {
        List<AppOrderDTO> order = orderService.getAllOrders();
        List<EntityModel<AppOrderDTO>> modelOrder = order.stream() //
                .map(orderDTOModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(modelOrder, linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    @GetMapping("/order/{id}")
    public EntityModel<AppOrderDTO> one(@PathVariable("id") Long id) {
        AppOrderDTO order = orderService.getOrderById(id);
        return orderDTOModelAssembler.toModel(order);
    }

    @GetMapping("/order")
    CollectionModel<EntityModel<AppOrderDTO>> allByUser(@RequestParam Long id) {
        Person person = personService.getPersonById(id);
        List<AppOrderDTO> orderList = orderService //
                .getOrderByUserId(person.getId());
        List<EntityModel<AppOrderDTO>> modelOrderList = orderList.stream() //
                .map(orderDTOModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(modelOrderList, linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    @PostMapping("/orders")
    ResponseEntity<?> newOrder(@RequestBody AppOrder newOrder) {
        Person person = personService.getPersonById(newOrder.getUserId());
        AppOrderDTO order = orderService
                .createOrder(
                    new AppOrder.Builder()
                    .orderStatus(OrderStatus.CREATED)
                    .creationDate(new Date())
                    .bindUser(person)
                    .build()
        );
        EntityModel<AppOrderDTO> entityModel = orderDTOModelAssembler //
                .toModel(order);
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

}
