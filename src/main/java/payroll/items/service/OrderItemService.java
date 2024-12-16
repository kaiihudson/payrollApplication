package payroll.items.service;

import org.springframework.stereotype.Service;
import payroll.order.model.AppOrder;
import payroll.items.model.OrderItem;
import payroll.items.repository.OrderItemRepository;
import payroll.order.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderRepository orderRepository;

    OrderItemService(OrderItemRepository orderItemRepository, OrderItemMapper orderItemMapper, OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemMapper = orderItemMapper;
        this.orderRepository = orderRepository;
    }

    public OrderItemDTO createOrderItem(OrderItem item) {
        OrderItem newItem = orderItemRepository.save(item);
        return orderItemMapper.mapToDTO(newItem);
    }

    public List<OrderItemDTO> getItemsByOrder(Long orderId) {
        Optional<AppOrder> order = orderRepository.findById(orderId);
        if (order.isPresent()){
            List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.get());
            return orderItems.stream()
                    .map(orderItemMapper::mapToDTO)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

}
