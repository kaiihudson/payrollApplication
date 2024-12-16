package payroll.items.service;

import org.springframework.stereotype.Component;
import payroll.items.model.OrderItem;

@Component
public class OrderItemMapper {
    OrderItemDTO mapToDTO(OrderItem orderItem) {
        return new OrderItemDTO.Builder()
                .setId(orderItem.getId())
                .setResponsible(orderItem.getResponsible())
                .setItemName(orderItem.getItemName())
                .setQuantity(orderItem.getQuantity())
                .setReportedPrice(orderItem.getReportedPrice())
                .setTotalPrice(orderItem.getTotalPrice())
                .setMainQuality(orderItem.getMainQuality())
                .setAlternativeQuality(orderItem.getAlternateQuality())
                .setSource(orderItem.getSource())
                .setOrderId(orderItem.getOrderId().getId())
                .build();
    }
    OrderItem mapFromDTO(OrderItemDTO orderItem) {
        return new OrderItem.Builder()
                .setId(orderItem.getId())
                .responsible(orderItem.getResponsible())
                .itemName(orderItem.getItemName())
                .quantity(orderItem.getQuantity())
                .alternateQuality(orderItem.getAlternativeQuality())
                .mainQuality(orderItem.getMainQuality())
                .reportedPrice(orderItem.getReportedPrice())
                .source(orderItem.getSource())
                .totalPrice(orderItem.getTotalPrice())
                .build();
    }
}
