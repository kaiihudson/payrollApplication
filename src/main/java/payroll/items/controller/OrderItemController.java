package payroll.items.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payroll.items.model.OrderItem;
import payroll.items.service.OrderItemDTO;
import payroll.items.service.OrderItemMapper;
import payroll.items.service.OrderItemService;
import payroll.order.model.AppOrder;
import payroll.order.service.OrderService;

import java.util.List;

/**
 * The type Order item controller.
 */
@RestController
@RequestMapping("/api/v1")
public class OrderItemController {
    private final OrderItemService orderItemService;
    private final OrderService orderService;

    /**
     * Instantiates a new Order item controller.
     *
     * @param orderItemService the order item service
     * @param orderService     the order service
     * @param orderItemMapper  the order item mapper
     */
    public OrderItemController(OrderItemService orderItemService, OrderService orderService, OrderItemMapper orderItemMapper) {
        this.orderItemService = orderItemService;
        this.orderService = orderService;
    }

    /**
     * Items by order response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/order/{id}/items")
    public ResponseEntity<List<OrderItemDTO>> itemsByOrder(@PathVariable("id") Long id) {
        List<OrderItemDTO> items = orderItemService.getItemsByOrder(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    /**
     * New item response entity.
     *
     * @param item the item
     * @param id   the id
     * @return the response entity
     */
    @PostMapping("/order/{id}/item")
    public ResponseEntity<?> newItem(@RequestBody OrderItem item, @PathVariable("id") Long id) {
        AppOrder order = orderService.getRawOrderById(id);
        OrderItemDTO newItem = orderItemService.createOrderItem(item, order);
        return new ResponseEntity<>(newItem, HttpStatus.OK);
    }

    /**
     * New items response entity.
     *
     * @param items the items
     * @param id    the id
     * @return the response entity
     */
    @PostMapping("/order/{id}/items")
    public ResponseEntity<?> newItems(@RequestBody List<OrderItem> items, @PathVariable("id") Long id) {
        AppOrder order = orderService.getRawOrderById(id);
        List<OrderItemDTO> newItems = orderItemService.createBatchOrderItems(items, order);
        return new ResponseEntity<>(newItems, HttpStatus.OK);
    }
}
