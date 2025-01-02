package payroll.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class OrderNotAbortedAdvice {
    @ExceptionHandler(OrderNotAbortedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String OrderNotAbortedHandler(OrderNotAbortedException ex) {
        return ex.getMessage();
    }
}
