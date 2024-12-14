package payroll.order.service;

import org.springframework.stereotype.Service;
import payroll.order.model.AppOrder;
import payroll.order.model.OrderItem;
import payroll.order.repository.OrderItemRepository;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem appendOrderItem(OrderItem item) {
        return orderItemRepository.save(item);
    }

    public List<OrderItem> getItemsByOrder(AppOrder order) {
        return orderItemRepository.findByOrderId(order);
    }

}
