package payroll.order.service;


import payroll.order.model.OrderStatus;

import java.util.Date;

/**
 * The type App order dto.
 */
public class AppOrderDTO {
    private Long id;
    private OrderStatus orderStatus;
    private Date creationDate;
    private Long userId;
    private String retailer;
    private Date executionDate;

    /**
     * Instantiates a new App order dto.
     *
     * @param build the build
     */
    public AppOrderDTO(Builder build) {
        this.id = build.id;
        this.orderStatus = build.orderStatus;
        this.creationDate = build.creationDate;
        this.userId = build.userId;
        this.retailer = build.retailer;
        this.executionDate = build.executionDate;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets order status.
     *
     * @return the order status
     */
    public OrderStatus getOrderStatus() {
        return orderStatus;
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
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
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
     * Gets execution date.
     *
     * @return the execution date
     */
    public Date getExecutionDate() {
        return executionDate;
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        private Long id;
        private OrderStatus orderStatus;
        private Date creationDate;
        private Long userId;
        private String retailer;
        private Date executionDate;

        /**
         * Sets id.
         *
         * @param id the id
         * @return the id
         */
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets order status.
         *
         * @param orderStatus the order status
         * @return the order status
         */
        public Builder setOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        /**
         * Sets creation date.
         *
         * @param creationDate the creation date
         * @return the creation date
         */
        public Builder setCreationDate(Date creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        /**
         * Sets user id.
         *
         * @param userId the user id
         * @return the user id
         */
        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        /**
         * Sets retailer.
         *
         * @param retailer the retailer
         * @return the retailer
         */
        public Builder setRetailer(String retailer) {
            this.retailer = retailer;
            return this;
        }

        /**
         * Sets execution date.
         *
         * @param date the date
         * @return the execution date
         */
        public Builder setExecutionDate(Date date) {
            this.executionDate = date;
            return this;
        }

        /**
         * Build app order dto.
         *
         * @return the app order dto
         */
        public AppOrderDTO build() {
            return new AppOrderDTO(this);
        }
    }

}
