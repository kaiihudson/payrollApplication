package payroll.order.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import payroll.order.*;
import payroll.order.model.AppOrder;
import payroll.order.model.OrderItem;
import payroll.order.model.OrderStatus;
import payroll.order.service.OrderItemService;
import payroll.order.service.OrderService;
import payroll.person.model.Person;
import payroll.person.service.PersonService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderService orderService;
    private final PersonService personService;
    private final OrderItemService orderItemService;
    private final OrderModelAssembler orderAssembler;
    private final OrderItemModelAssembler orderItemModelAssembler;

    OrderController(OrderService orderService, PersonService personService, OrderItemService orderItemService, OrderModelAssembler orderAssembler, OrderItemModelAssembler orderItemModelAssembler) {
        this.orderService = orderService;
        this.personService = personService;
        this.orderItemService = orderItemService;
        this.orderAssembler = orderAssembler;
        this.orderItemModelAssembler = orderItemModelAssembler;
    }


    @GetMapping("/orders")
    public CollectionModel<EntityModel<AppOrder>> all() {
        List<AppOrder> order = orderService.getAllOrders();
        List<EntityModel<AppOrder>> modelOrder = order.stream() //
                .map(orderAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(modelOrder, linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    @GetMapping("/order/{id}")
    public EntityModel<AppOrder> one(@PathVariable("id") Long id) {
        AppOrder order = orderService.getOrderById(id);
        return orderAssembler.toModel(order);
    }

    @GetMapping("/order/?user={id}")
    CollectionModel<EntityModel<AppOrder>> allByUser(@PathVariable Long id) {
        List<AppOrder> orderList = orderService //
                .getOrderByUserId(personService.getPersonById(id));
        List<EntityModel<AppOrder>> modelOrderList = orderList.stream() //
                .map(orderAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(modelOrderList, linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    @PostMapping("/orders")
    ResponseEntity<?> newOrder(@RequestBody AppOrder newOrder) {
        Person person = personService.getPersonById(newOrder.getUserId());
        newOrder.setBindUser(person);
        newOrder.setOrderStatus(OrderStatus.CREATED);
        EntityModel<AppOrder> entityModel = orderAssembler //
                .toModel(orderService.createOrder(newOrder));
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/items/?order={id}")
    CollectionModel<EntityModel<OrderItem>> allItemsInOrder(@PathVariable Long id) {
        List<OrderItem> itemList = orderItemService
                .getItemsByOrder(orderService.getOrderById(id));
        List<EntityModel<OrderItem>> modelItemList = itemList.stream() //
                .map(orderItemModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(modelItemList, linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

}
