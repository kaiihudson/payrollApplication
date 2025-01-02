package payroll.items.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import payroll.items.model.OrderItem;
import payroll.order.model.AppOrder;

import java.util.Collection;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(AppOrder appOrder);
    List<OrderItem> findByOrderIdInOrderByRetailer(Collection<AppOrder> orderList);

    @Query("SELECT SUM(e.totalPrice) FROM OrderItem e WHERE e.orderId = :id")
    Float sumTotalPriceByOrderId(@Param("id") AppOrder id);
}
