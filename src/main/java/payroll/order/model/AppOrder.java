package payroll.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import payroll.person.model.Person;

import java.util.*;

@Entity
@Table(name = "orders")
public class AppOrder {
    private @Id
    @GeneratedValue Long id;
    private OrderStatus orderStatus;
    private Date creationDate;
    private Date executionDate;
    private String retailer;

    @Transient
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_orders", nullable = false)
    private Person bindUser;

    AppOrder() {
    }

    public AppOrder(Builder build) {
        this.orderStatus = build.orderStatus;
        this.creationDate = build.creationDate;
        this.executionDate = build.executionDate;
        this.userId = build.userId;
        this.bindUser = build.bindUser;
        this.retailer = build.retailer;
    }

    public Long getId() {
        return this.id;
    }

    public Person getBindUser() {
        return this.bindUser;
    }

    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public String getRetailer() {
        return retailer;
    }

    public static class Builder {

        private Long id;
        private OrderStatus orderStatus;
        private Date creationDate;
        private Date executionDate;
        private Long userId;
        private Person bindUser;
        private String retailer;

        public Builder executionDate(Date executionDate) {
            this.executionDate = executionDate;
            return this;
        }

        public Builder creationDate(Date creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder orderStatus(OrderStatus status) {
            this.orderStatus = status;
            return this;
        }

        public Builder bindUser(Person bindUser) {
            this.bindUser = bindUser;
            return this;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder retailer(String retailer) {
            this.retailer = retailer;
            return this;
        }

        public AppOrder build(){
            return new AppOrder(this);
        }

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
        return Objects.hash(this.id, this.bindUser);
    }
}
