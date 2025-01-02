package payroll.order.model;

import jakarta.persistence.*;
import payroll.person.model.Person;

import java.util.*;

/**
 * The type App order.
 */
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

    /**
     * Instantiates a new App order.
     */
    AppOrder() {
    }

    /**
     * Instantiates a new App order.
     *
     * @param build the build
     */
    public AppOrder(Builder build) {
        this.orderStatus = build.orderStatus;
        this.creationDate = build.creationDate;
        this.executionDate = build.executionDate;
        this.userId = build.userId;
        this.bindUser = build.bindUser;
        this.retailer = build.retailer;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Gets bind user.
     *
     * @return the bind user
     */
    public Person getBindUser() {
        return this.bindUser;
    }

    /**
     * Gets order status.
     *
     * @return the order status
     */
    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return this.userId;
    }

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Gets execution date.
     *
     * @return the execution date
     */
    public Date getExecutionDate() {
        return executionDate;
    }

    /**
     * Gets retailer.
     *
     * @return the retailer
     */
    public String getRetailer() {
        return retailer;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets order status.
     *
     * @param orderStatus the order status
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Sets creation date.
     *
     * @param creationDate the creation date
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Sets execution date.
     *
     * @param executionDate the execution date
     */
    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    /**
     * Sets retailer.
     *
     * @param retailer the retailer
     */
    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Sets bind user.
     *
     * @param bindUser the bind user
     */
    public void setBindUser(Person bindUser) {
        this.bindUser = bindUser;
    }

    /**
     * The type Builder.
     */
    public static class Builder {

        private Long id;
        private OrderStatus orderStatus;
        private Date creationDate;
        private Date executionDate;
        private Long userId;
        private Person bindUser;
        private String retailer;

        /**
         * Execution date builder.
         *
         * @param executionDate the execution date
         * @return the builder
         */
        public Builder executionDate(Date executionDate) {
            this.executionDate = executionDate;
            return this;
        }

        /**
         * Creation date builder.
         *
         * @param creationDate the creation date
         * @return the builder
         */
        public Builder creationDate(Date creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        /**
         * User id builder.
         *
         * @param userId the user id
         * @return the builder
         */
        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        /**
         * Order status builder.
         *
         * @param status the status
         * @return the builder
         */
        public Builder orderStatus(OrderStatus status) {
            this.orderStatus = status;
            return this;
        }

        /**
         * Bind user builder.
         *
         * @param bindUser the bind user
         * @return the builder
         */
        public Builder bindUser(Person bindUser) {
            this.bindUser = bindUser;
            return this;
        }

        /**
         * Id builder.
         *
         * @param id the id
         * @return the builder
         */
        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Retailer builder.
         *
         * @param retailer the retailer
         * @return the builder
         */
        public Builder retailer(String retailer) {
            this.retailer = retailer;
            return this;
        }

        /**
         * Build app order.
         *
         * @return the app order
         */
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
