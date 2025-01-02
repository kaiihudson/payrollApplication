package payroll.order.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import payroll.order.model.AppOrder;
import payroll.order.model.OrderUpdate;
import payroll.order.service.AppOrderDTO;
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
    private final OrderDTOModelAssembler orderDTOModelAssembler;

    public OrderController(OrderService orderService, PersonService personService, OrderDTOModelAssembler orderDTOModelAssembler) {
        this.orderService = orderService;
        this.personService = personService;
        this.orderDTOModelAssembler = orderDTOModelAssembler;
    }

    @GetMapping("/orders")
    public CollectionModel<EntityModel<AppOrderDTO>> all() {
        List<AppOrderDTO> order = orderService.getAllOrdersWithTotals();
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
        List<AppOrderDTO> orderList = orderService //
                .getIncompleteOrderByUserId(id);
        List<EntityModel<AppOrderDTO>> modelOrderList = orderList.stream() //
                .map(orderDTOModelAssembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(modelOrderList, linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    @GetMapping("/complete_order")
    HttpEntity<PagedModel<AppOrderDTO>> allByUserWhereComplete(
            @RequestParam Long id,
            @PageableDefault(size = 5) Pageable p,
            PagedResourcesAssembler pageAssembler
    ) {
        Page<EntityModel<AppOrderDTO>> orderList = orderService //
                .getPreviousOrderByUserId(id, p);
        return ResponseEntity.ok(pageAssembler.toModel(orderList));
    }

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

    @DeleteMapping("/order/{id}")
    ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        AppOrderDTO order = orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/orderList")
    ResponseEntity<?> updateOrders(@RequestBody OrderUpdate orderUpdate) {
        orderService.updateList(orderUpdate);
        return ResponseEntity.ok("");
    }

}
