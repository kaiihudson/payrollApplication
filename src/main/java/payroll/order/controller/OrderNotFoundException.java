package payroll.order.controller;

/**
 * The type Order not found exception.
 */
public class OrderNotFoundException extends RuntimeException{
    /**
     * Instantiates a new Order not found exception.
     *
     * @param id the id
     */
    public OrderNotFoundException(Long id) {super("cannot find order with id: " + id );}
}
