package payroll.items.model;

import jakarta.persistence.*;
import payroll.order.model.AppOrder;

/**
 * The type Order item.
 */
@Entity
@Table(name = "order_items")
public class OrderItem {

    private @Id
    @GeneratedValue Long id;
    private String responsible;
    private String itemName;
    private int quantity;
    private float reportedPrice;
    private float totalPrice;
    private Quality mainQuality;
    private Quality alternateQuality;
    private String source;
    private Status itemStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_order_id", nullable = false)
    private AppOrder orderId;

    /**
     * Instantiates a new Order item.
     */
    OrderItem() {
    }

    /**
     * Instantiates a new Order item.
     *
     * @param builder the builder
     */
    OrderItem(Builder builder) {
        this.responsible = builder.responsible;
        this.itemName = builder.itemName;
        this.quantity = builder.quantity;
        this.reportedPrice = builder.reportedPrice;
        this.totalPrice = builder.totalPrice;
        this.mainQuality = builder.mainQuality;
        this.alternateQuality = builder.alternateQuality;
        this.source = builder.source;
        this.orderId = builder.orderId;
        this.itemStatus = builder.itemStatus;
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
     * Gets alternate quality.
     *
     * @return the alternate quality
     */
    public Quality getAlternateQuality() {
        return alternateQuality;
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
    public AppOrder getOrderId() {
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
        private Quality alternateQuality;
        private String source;
        private AppOrder orderId;
        private Status itemStatus;

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
         * Responsible builder.
         *
         * @param responsible the responsible
         * @return the builder
         */
        public Builder responsible(String responsible) {
            this.responsible = responsible;
            return this;
        }

        /**
         * Item name builder.
         *
         * @param itemName the item name
         * @return the builder
         */
        public Builder itemName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        /**
         * Quantity builder.
         *
         * @param quantity the quantity
         * @return the builder
         */
        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        /**
         * Reported price builder.
         *
         * @param reportedPrice the reported price
         * @return the builder
         */
        public Builder reportedPrice(float reportedPrice) {
            this.reportedPrice = reportedPrice;
            return this;
        }

        /**
         * Total price builder.
         *
         * @param totalPrice the total price
         * @return the builder
         */
        public Builder totalPrice(float totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        /**
         * Main quality builder.
         *
         * @param mainQuality the main quality
         * @return the builder
         */
        public Builder mainQuality(Quality mainQuality) {
            this.mainQuality = mainQuality;
            return this;
        }

        /**
         * Alternate quality builder.
         *
         * @param alternateQuality the alternate quality
         * @return the builder
         */
        public Builder alternateQuality(Quality alternateQuality) {
            this.alternateQuality = alternateQuality;
            return this;
        }

        /**
         * Source builder.
         *
         * @param source the source
         * @return the builder
         */
        public Builder source(String source) {
            this.source = source;
            return this;
        }

        /**
         * Order id builder.
         *
         * @param orderId the order id
         * @return the builder
         */
        public Builder orderId(AppOrder orderId) {
            this.orderId = orderId;
            return this;
        }

        /**
         * Status builder.
         *
         * @param status the status
         * @return the builder
         */
        public Builder status(Status status) {
            this.itemStatus = status;
            return this;
        }

        /**
         * Build order item.
         *
         * @return the order item
         */
        public OrderItem build(){
            return new OrderItem(this);
        }
    }

}
