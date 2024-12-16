package payroll.items.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import payroll.items.model.OrderItem;
import payroll.order.model.AppOrder;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(AppOrder appOrder);
}
