package payroll.order.service;

import payroll.order.model.AppOrder;
import payroll.order.model.OrderStatus;

import java.util.Date;

public class AppOrderDTO {
    private Long id;
    private OrderStatus orderStatus;
    private Date creationDate;
    private Long userId;
    private String retailer;

    public AppOrderDTO(Builder build) {
        this.id = build.id;
        this.orderStatus = build.orderStatus;
        this.creationDate = build.creationDate;
        this.userId = build.userId;
        this.retailer = build.retailer;
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

    public static class Builder {
        private Long id;
        private OrderStatus orderStatus;
        private Date creationDate;
        private Long userId;
        private String retailer;

        public Builder setId(Long id) {
            this.id = id;
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

        public AppOrderDTO build() {
            return new AppOrderDTO(this);
        }
    }

}
