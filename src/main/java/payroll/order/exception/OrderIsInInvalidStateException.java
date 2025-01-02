package payroll.order.exception;

import payroll.order.model.OrderStatus;

public class OrderIsInInvalidStateException extends RuntimeException {
    public OrderIsInInvalidStateException(OrderStatus orderStatus) {
        super("order is in invalid state: " + orderStatus );
    }
}
