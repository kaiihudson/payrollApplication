package payroll.items.service;

import org.springframework.stereotype.Component;
import payroll.items.model.OrderItem;

/**
 * The type Order item mapper.
 */
@Component
public class OrderItemMapper {
    /**
     * Map to dto order item dto.
     *
     * @param orderItem the order item
     * @return the order item dto
     */
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
                .setItemStatus(orderItem.getItemStatus())
                .build();
    }

    /**
     * Map from dto order item.
     *
     * @param orderItem the order item
     * @return the order item
     */
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
                .status(orderItem.getItemStatus())
                .build();
    }
}
