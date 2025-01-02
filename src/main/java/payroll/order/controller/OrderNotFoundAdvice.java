package payroll.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The type Order not found advice.
 */
@RestControllerAdvice
class OrderNotFoundAdvice {
    /**
     * Order not found handler string.
     *
     * @param ex the ex
     * @return the string
     */
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String OrderNotFoundHandler(OrderNotFoundException ex) {
        return ex.getMessage();
    }
}
