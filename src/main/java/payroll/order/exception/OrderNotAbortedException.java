package payroll.order.exception;

public class OrderNotAbortedException extends RuntimeException {
    public OrderNotAbortedException(Long id) {
        super("Cannot abort order with id: " + id + " , since is already aborted");
    }
}
