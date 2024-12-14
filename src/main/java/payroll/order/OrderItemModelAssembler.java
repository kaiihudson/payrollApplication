package payroll.order;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import payroll.order.controller.OrderController;
import payroll.order.model.OrderItem;

@Component
public class OrderItemModelAssembler implements RepresentationModelAssembler<OrderItem, EntityModel<OrderItem>> {

    @Override
    public EntityModel<OrderItem> toModel(OrderItem orderItem){
        return EntityModel.of(orderItem, //
                linkTo(methodOn(OrderController.class).one(orderItem.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).all()).withRel("order")
        );
    }
}