package payroll.items.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import payroll.items.model.OrderItem;
import payroll.order.model.AppOrder;

import java.util.List;

/**
 * The interface Order item repository.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    /**
     * Find by order id list.
     *
     * @param appOrder the app order
     * @return the list
     */
    List<OrderItem> findByOrderId(AppOrder appOrder);
}
