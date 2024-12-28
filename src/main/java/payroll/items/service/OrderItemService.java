package payroll.items.service;

import org.aspectj.weaver.ast.Or;
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

    private OrderItem addOrderToItem(OrderItem item, AppOrder order){
        return new OrderItem.Builder()
                .responsible(item.getResponsible())
                .itemName(item.getItemName())
                .quantity(item.getQuantity())
                .reportedPrice(item.getReportedPrice())
                .mainQuality(item.getMainQuality())
                .alternateQuality(item.getAlternateQuality())
                .source(item.getSource())
                .orderId(order)
                .build();
    }

    public OrderItemDTO createOrderItem(OrderItem item, AppOrder order) {
        OrderItem newItem = orderItemRepository.save(this.addOrderToItem(item, order));
        return orderItemMapper.mapToDTO(newItem);
    }
    private List<OrderItem> addOrdertoItemList(List<OrderItem> items, AppOrder order) {
        List<OrderItem> cleanItemList = new ArrayList<>();
        for (OrderItem subject : items) {
            OrderItem result = new OrderItem.Builder()
                    .responsible(subject.getResponsible())
                    .itemName(subject.getItemName())
                    .quantity(subject.getQuantity())
                    .reportedPrice(subject.getReportedPrice())
                    .totalPrice(subject.getTotalPrice())
                    .mainQuality(subject.getMainQuality())
                    .alternateQuality(subject.getAlternateQuality())
                    .source(subject.getSource())
                    .orderId(order)
                    .build();
            cleanItemList.add(result);
        }
        return cleanItemList;
    }
    public List<OrderItemDTO> createBatchOrderItems(List<OrderItem> items, AppOrder order) {
        List<OrderItem> newItems = orderItemRepository.saveAll(this.addOrdertoItemList(items, order));
        return newItems.stream().map(orderItemMapper::mapToDTO).collect(Collectors.toList());
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
