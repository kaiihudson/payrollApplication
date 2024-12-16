package payroll.items.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payroll.items.model.OrderItem;
import payroll.items.service.OrderItemDTO;
import payroll.items.service.OrderItemMapper;
import payroll.items.service.OrderItemService;
import payroll.order.controller.OrderController;
import payroll.order.model.AppOrder;
import payroll.order.service.AppOrderDTO;
import payroll.order.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1")
public class OrderItemController {
    private final OrderItemService orderItemService;
    private final OrderService orderService;
    private final OrderItemMapper orderItemMapper;

    public OrderItemController(OrderItemService orderItemService, OrderService orderService, OrderItemMapper orderItemMapper) {
        this.orderItemService = orderItemService;
        this.orderService = orderService;
        this.orderItemMapper = orderItemMapper;
    }

    @GetMapping("/order/{id}/items")
    public ResponseEntity<List<OrderItemDTO>> itemsByOrder(@PathVariable("id") Long id) {
        List<OrderItemDTO> items = orderItemService.getItemsByOrder(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("/order/{id}/items")
    public ResponseEntity<?> newItem(@RequestBody OrderItem item, @PathVariable("id") Long id) {
        AppOrder order = orderService.getRawOrderById(id);
        OrderItemDTO newItem = orderItemService
                .createOrderItem(
                        new OrderItem.Builder()
                                .responsible(item.getResponsible())
                                .itemName(item.getItemName())
                                .quantity(item.getQuantity())
                                .reportedPrice(item.getReportedPrice())
                                .mainQuality(item.getMainQuality())
                                .alternateQuality(item.getAlternateQuality())
                                .source(item.getSource())
                                .orderId(order)
                                .build()
                );
        return new ResponseEntity<>(newItem, HttpStatus.OK);
    }
}
