package payroll.order.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import payroll.order.model.AppOrder;
import payroll.order.model.OrderStatus;
import payroll.person.model.Person;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<AppOrder, Long> {
    List<AppOrder> findByBindUserAndOrderStatusNotInOrderByRetailerAsc(Person person_id, Collection<OrderStatus> status);

    Page<AppOrder> findByBindUserAndOrderStatusInOrderByOrderStatusAsc(Person person_id, Collection<OrderStatus> status, Pageable p);

    Optional<List<AppOrder>> findByBindUserAndOrderStatusNotIn(Person person, Collection<OrderStatus> status);

    List<AppOrder> findByOrderStatusNotInOrderByOrderStatus(Collection<OrderStatus> status);

    List<AppOrder> findByIdIn(Collection<Long> ids);
}
