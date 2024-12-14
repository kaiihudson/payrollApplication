package payroll.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import payroll.order.model.AppOrder;
import payroll.order.model.OrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(AppOrder appOrder);
}
