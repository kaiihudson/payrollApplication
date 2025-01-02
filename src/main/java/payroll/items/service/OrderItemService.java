package payroll.items.service;


import org.springframework.stereotype.Service;
import payroll.items.exception.OrderIsProcessing;
import payroll.items.model.ItemsStatus;
import payroll.order.model.AppOrder;
import payroll.items.model.OrderItem;
import payroll.items.repository.OrderItemRepository;
import payroll.order.model.OrderStatus;
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
                .status(ItemsStatus.CREATED)
                .orderId(order)
                .build();
    }

    public OrderItemDTO createOrderItem(OrderItem item, AppOrder order) {
        Optional<AppOrder> newOrder = orderRepository.findById(order.getId());
        if (newOrder.isPresent()) {
            if (newOrder.get().getOrderStatus() == OrderStatus.CREATED) {
                OrderItem newItem = orderItemRepository.save(this.addOrderToItem(item, newOrder.get()));
                return orderItemMapper.mapToDTO(newItem);
            } else {
                throw new OrderIsProcessing(newOrder.get().getId());
            }
        }
        return null;
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
                    .status(ItemsStatus.CREATED)
                    .retailer(subject.getRetailer())
                    .orderId(order)
                    .build();
            cleanItemList.add(result);
        }
        return cleanItemList;
    }

    public List<OrderItemDTO> createBatchOrderItems(List<OrderItem> items, AppOrder order) {
        Optional<AppOrder> newOrder = orderRepository.findById(order.getId());
        if (newOrder.isPresent()) {
            if (newOrder.get().getOrderStatus() == OrderStatus.CREATED) {
                List<OrderItem> newItems = orderItemRepository.saveAll(this.addOrdertoItemList(items, order));
                return newItems.stream().map(orderItemMapper::mapToDTO).collect(Collectors.toList());
            } else {
                throw new OrderIsProcessing(newOrder.get().getId());
            }
        }
        return null;
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

    public List<OrderItemDTO> getItemsByOrderList(List<Long> orderIdList) {
        List<AppOrder> orders = orderRepository.findByIdIn(orderIdList);
        return orderItemRepository.findByOrderIdInOrderByRetailer(orders)
                .stream()
                .map(orderItemMapper::mapToDTO)
                .collect(Collectors.toList());
    }
}
