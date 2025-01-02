package payroll.items.exception;

public class OrderIsProcessing extends RuntimeException {
    public OrderIsProcessing(Long id) {
        super("Order #" + id + " is already being processed");
    }
}
