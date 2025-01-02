package payroll.order.service;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import payroll.items.repository.OrderItemRepository;
import payroll.order.controller.OrderController;
import payroll.order.model.AppOrder;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AppOrderMapper {
    public AppOrderDTO mapToDTO(AppOrder order){
        return new AppOrderDTO.Builder()
                .setCreationDate(order.getCreationDate())
                .setOrderStatus(order.getOrderStatus())
                .setUserId(order.getBindUser().getId())
                .setRetailer(order.getRetailer())
                .setExecutionDate(order.getExecutionDate())
                .setId(order.getId())
                .build();
    }
    public AppOrderDTO mapToDTOWithTotal(AppOrder order, Float total) {
        return new AppOrderDTO.Builder()
                .setCreationDate(order.getCreationDate())
                .setOrderStatus(order.getOrderStatus())
                .setUserId(order.getBindUser().getId())
                .setRetailer(order.getRetailer())
                .setExecutionDate(order.getExecutionDate())
                .setOrderTotal(total)
                .setId(order.getId())
                .build();
    }
    public EntityModel<AppOrderDTO> mapToEntityModel(AppOrderDTO order) {
        return EntityModel.of(order,
                linkTo(methodOn(OrderController.class).one(order.getId())).withSelfRel()
                );
    }
}
