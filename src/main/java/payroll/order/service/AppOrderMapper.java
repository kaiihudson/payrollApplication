package payroll.order.service;

import org.springframework.stereotype.Component;
import payroll.order.model.AppOrder;

@Component
public class AppOrderMapper {
    AppOrderDTO mapToDTO(AppOrder order){
        return new AppOrderDTO.Builder()
                .setCreationDate(order.getCreationDate())
                .setOrderStatus(order.getOrderStatus())
                .setUserId(order.getBindUser().getId())
                .setRetailer(order.getRetailer())
                .setId(order.getId())
                .build();
    }
}
