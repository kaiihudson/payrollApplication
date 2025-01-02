package payroll.items.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class OrderIsProcessingAdvice {
    @ExceptionHandler(OrderIsProcessing.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String OrderIsNotCreatedHandler(OrderIsProcessing ex) {
        return ex.getMessage();
    }
}
