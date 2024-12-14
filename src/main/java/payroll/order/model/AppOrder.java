package payroll.order.model;

import jakarta.persistence.*;
import payroll.person.model.Person;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class AppOrder {
    private @Id
    @GeneratedValue Long id;

    @Transient
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_orders", nullable = false)
    private Person bindUser;

    @OneToMany(mappedBy = "orderId", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    private OrderStatus orderStatus;
    private LocalDateTime creationDate;
    private LocalDateTime executionDate;

    AppOrder() {
    }

    AppOrder(
            Long userId
    ) {
        this.userId = userId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Person getBindUser() {
        return this.bindUser;
    }

    public void setBindUser(Person bindUser) {
        this.bindUser = bindUser;
    }

    public List<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(OrderStatus status) {
        this.orderStatus = status;
    }

    public void appendOrderItem(OrderItem orderItem) {
        OrderItem[] orders = this.orderItems.toArray(new OrderItem[0]);
        OrderItem[] newArr = Arrays.copyOf(orders, orders.length + 1);
        newArr[orders.length - 1] = orderItem;
        this.orderItems = List.of(newArr);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof AppOrder order))
            return false;
        return Objects.equals(this.id, order.id) && Objects.equals(this.bindUser, order.bindUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.bindUser, this.orderItems);
    }
}
