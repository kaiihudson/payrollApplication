package payroll.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import payroll.order.model.AppOrder;
import payroll.order.model.OrderStatus;
import payroll.person.model.Person;

import java.util.List;

public interface OrderRepository extends JpaRepository<AppOrder, Long> {
    List<AppOrder> findByBindUserAndOrderStatusNotOrderByRetailerAsc(Person person_id, OrderStatus status);
    List<AppOrder> findByBindUserAndOrderStatus(Person person_id, OrderStatus status);
}
