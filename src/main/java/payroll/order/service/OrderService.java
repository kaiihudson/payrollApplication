package payroll.order.service;

import org.springframework.stereotype.Service;
import payroll.order.OrderNotFoundException;
import payroll.order.model.AppOrder;
import payroll.order.repository.OrderItemRepository;
import payroll.order.repository.OrderRepository;
import payroll.person.model.Person;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
    }

    public List<AppOrder> getAllOrders() {
        List<AppOrder> orders = orderRepository.findAll();
//      [ 0, 1, 2]
//      0|1|2 = {a: a, b: b, c: c}
//      tech wizards stuff
//      remueve la llave b
//      a = {a: a, c: c}

        return orders;
    }

    public AppOrder getOrderById(Long id) {
        return orderRepository.findById(id) //
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    public List<AppOrder> getOrderByUserId(Person person) {
        return orderRepository.findByBindUser(person);
    }

    public AppOrder createOrder(AppOrder order) {
        return orderRepository.save(order);
    }
}
