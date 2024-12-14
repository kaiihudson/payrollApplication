package payroll.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import payroll.order.model.AppOrder;
import payroll.person.model.Person;

import java.util.List;

public interface OrderRepository extends JpaRepository<AppOrder, Long> {
    List<AppOrder> findByBindUser(Person person);
}
