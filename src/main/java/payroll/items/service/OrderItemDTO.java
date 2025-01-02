package payroll.items.service;

import payroll.items.model.Quality;
import payroll.items.model.Status;

/**
 * The type Order item dto.
 */
public class OrderItemDTO {
    private Long id;
    private String responsible;
    private String itemName;
    private int quantity;
    private float reportedPrice;
    private float totalPrice;
    private Quality mainQuality;
    private Quality alternativeQuality;
    private String source;
    private Long orderId;
    private Status itemStatus;

    /**
     * Instantiates a new Order item dto.
     *
     * @param build the build
     */
    public OrderItemDTO(Builder build) {
        this.id = build.id;
        this.responsible = build.responsible;
        this.itemName = build.itemName;
        this.quantity = build.quantity;
        this.reportedPrice = build.reportedPrice;
        this.totalPrice = build.totalPrice;
        this.mainQuality = build.mainQuality;
        this.alternativeQuality = build.alternativeQuality;
        this.source = build.source;
        this.orderId = build.orderId;
        this.itemStatus = build.itemStatus;
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
     * Gets responsible.
     *
     * @return the responsible
     */
    public String getResponsible() {
        return responsible;
    }

    /**
     * Gets item name.
     *
     * @return the item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets reported price.
     *
     * @return the reported price
     */
    public float getReportedPrice() {
        return reportedPrice;
    }

    /**
     * Gets total price.
     *
     * @return the total price
     */
    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * Gets main quality.
     *
     * @return the main quality
     */
    public Quality getMainQuality() {
        return mainQuality;
    }

    /**
     * Gets alternative quality.
     *
     * @return the alternative quality
     */
    public Quality getAlternativeQuality() {
        return alternativeQuality;
    }

    /**
     * Gets source.
     *
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * Gets order id.
     *
     * @return the order id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * Gets item status.
     *
     * @return the item status
     */
    public Status getItemStatus() {
        return itemStatus;
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        private Long id;
        private String responsible;
        private String itemName;
        private int quantity;
        private float reportedPrice;
        private float totalPrice;
        private Quality mainQuality;
        private Quality alternativeQuality;
        private String source;
        private Long orderId;
        private Status itemStatus;

        /**
         * Build order item dto.
         *
         * @return the order item dto
         */
        public OrderItemDTO build() {
            return new OrderItemDTO(this);
        }

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
         * Sets responsible.
         *
         * @param responsible the responsible
         * @return the responsible
         */
        public Builder setResponsible(String responsible) {
            this.responsible = responsible;
            return this;
        }

        /**
         * Sets item name.
         *
         * @param itemName the item name
         * @return the item name
         */
        public Builder setItemName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        /**
         * Sets quantity.
         *
         * @param quantity the quantity
         * @return the quantity
         */
        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        /**
         * Sets reported price.
         *
         * @param reportedPrice the reported price
         * @return the reported price
         */
        public Builder setReportedPrice(float reportedPrice) {
            this.reportedPrice = reportedPrice;
            return this;
        }

        /**
         * Sets total price.
         *
         * @param totalPrice the total price
         * @return the total price
         */
        public Builder setTotalPrice(float totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        /**
         * Sets main quality.
         *
         * @param mainQuality the main quality
         * @return the main quality
         */
        public Builder setMainQuality(Quality mainQuality) {
            this.mainQuality = mainQuality;
            return this;
        }

        /**
         * Sets alternative quality.
         *
         * @param alternativeQuality the alternative quality
         * @return the alternative quality
         */
        public Builder setAlternativeQuality(Quality alternativeQuality) {
            this.alternativeQuality = alternativeQuality;
            return this;
        }

        /**
         * Sets source.
         *
         * @param source the source
         * @return the source
         */
        public Builder setSource(String source) {
            this.source = source;
            return this;
        }

        /**
         * Sets order id.
         *
         * @param orderId the order id
         * @return the order id
         */
        public Builder setOrderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        /**
         * Sets item status.
         *
         * @param status the status
         * @return the item status
         */
        public Builder setItemStatus(Status status) {
            this.itemStatus = status;
            return this;
        }
    }
}
