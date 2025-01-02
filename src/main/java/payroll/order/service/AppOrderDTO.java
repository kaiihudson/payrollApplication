package payroll.order.service;


import payroll.order.model.OrderStatus;

import java.util.Date;

public class AppOrderDTO {
    private Long id;
    private OrderStatus orderStatus;
    private Date creationDate;
    private Long userId;
    private String retailer;
    private Date executionDate;
    private Float orderTotal;

    public AppOrderDTO(Builder build) {
        this.id = build.id;
        this.orderStatus = build.orderStatus;
        this.creationDate = build.creationDate;
        this.userId = build.userId;
        this.retailer = build.retailer;
        this.executionDate = build.executionDate;
        this.orderTotal = build.orderTotal;
    }

    public Long getId() {
        return id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Long getUserId() {
        return userId;
    }

    public String getRetailer() {
        return retailer;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public Float getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Float orderTotal) {
        this.orderTotal = orderTotal;
    }

    public static class Builder {
        private Long id;
        private OrderStatus orderStatus;
        private Date creationDate;
        private Long userId;
        private String retailer;
        private Date executionDate;
        private Float orderTotal;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setOrderTotal(float orderTotal) {
            this.orderTotal = orderTotal;
            return this;
        }

        public Builder setOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Builder setCreationDate(Date creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setRetailer(String retailer) {
            this.retailer = retailer;
            return this;
        }

        public Builder setExecutionDate(Date date) {
            this.executionDate = date;
            return this;
        }

        public AppOrderDTO build() {
            return new AppOrderDTO(this);
        }
    }

}
