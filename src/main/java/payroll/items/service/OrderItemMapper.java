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
                .setAlternateQuality(orderItem.getAlternateQuality())
                .setSource(orderItem.getSource())
                .setOrderId(orderItem.getOrderId().getId())
                .setItemStatus(orderItem.getItemStatus())
                .setRetailer(orderItem.getRetailer())
                .build();
    }

    OrderItem mapFromDTO(OrderItemDTO orderItem) {
        return new OrderItem.Builder()
                .setId(orderItem.getId())
                .responsible(orderItem.getResponsible())
                .itemName(orderItem.getItemName())
                .quantity(orderItem.getQuantity())
                .alternateQuality(orderItem.getAlternateQuality())
                .mainQuality(orderItem.getMainQuality())
                .reportedPrice(orderItem.getReportedPrice())
                .source(orderItem.getSource())
                .totalPrice(orderItem.getTotalPrice())
                .status(orderItem.getItemStatus())
                .build();
    }
}
