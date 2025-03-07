package payroll.order.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import payroll.order.model.AppOrder;

/**
 * The type Order model assembler.
 */
@Component
public class OrderModelAssembler implements RepresentationModelAssembler<AppOrder, EntityModel<AppOrder>> {

    @Override
    public EntityModel<AppOrder> toModel(AppOrder order){
        return EntityModel.of(order, //
                linkTo(methodOn(OrderController.class).one(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).all()).withRel("order")
        );
    }
}