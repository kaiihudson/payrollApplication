package payroll.order.controller;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Long id) {super("cannot find order with id: " + id );}
}
