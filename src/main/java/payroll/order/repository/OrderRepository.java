package payroll.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import payroll.order.model.AppOrder;
import payroll.order.model.OrderStatus;
import payroll.person.model.Person;

import java.util.List;

/**
 * The interface Order repository.
 */
public interface OrderRepository extends JpaRepository<AppOrder, Long> {
    /**
     * Find by bind user and order status not order by retailer asc list.
     *
     * @param person_id the person id
     * @param status    the status
     * @return the list
     */
    List<AppOrder> findByBindUserAndOrderStatusNotOrderByRetailerAsc(Person person_id, OrderStatus status);

    /**
     * Find by bind user and order status list.
     *
     * @param person_id the person id
     * @param status    the status
     * @return the list
     */
    List<AppOrder> findByBindUserAndOrderStatus(Person person_id, OrderStatus status);
}
