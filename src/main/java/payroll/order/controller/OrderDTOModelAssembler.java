package payroll.order.controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import payroll.order.service.AppOrderDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * The type Order dto model assembler.
 */
@Component
public class OrderDTOModelAssembler implements RepresentationModelAssembler<AppOrderDTO, EntityModel<AppOrderDTO>> {

    @Override
    public EntityModel<AppOrderDTO> toModel(AppOrderDTO order){
        return EntityModel.of(order, //
                linkTo(methodOn(OrderController.class).one(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).all()).withRel("order")
        );
    }
}