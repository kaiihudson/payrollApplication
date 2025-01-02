package payroll.order.service;

import org.springframework.stereotype.Component;
import payroll.order.model.AppOrder;

/**
 * The type App order mapper.
 */
@Component
public class AppOrderMapper {
    /**
     * Map to dto app order dto.
     *
     * @param order the order
     * @return the app order dto
     */
    AppOrderDTO mapToDTO(AppOrder order){
        return new AppOrderDTO.Builder()
                .setCreationDate(order.getCreationDate())
                .setOrderStatus(order.getOrderStatus())
                .setUserId(order.getBindUser().getId())
                .setRetailer(order.getRetailer())
                .setExecutionDate(order.getExecutionDate())
                .setId(order.getId())
                .build();
    }
}
