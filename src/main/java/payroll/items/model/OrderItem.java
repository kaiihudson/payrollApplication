package payroll.items.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;
import payroll.order.model.AppOrder;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_order_id", nullable = false)
    private AppOrder orderId;

    OrderItem() {
    }

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
    }

    public Long getId() {
        return id;
    }

    public String getResponsible() {
        return responsible;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getReportedPrice() {
        return reportedPrice;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public Quality getMainQuality() {
        return mainQuality;
    }

    public Quality getAlternateQuality() {
        return alternateQuality;
    }

    public String getSource() {
        return source;
    }

    public AppOrder getOrderId() {
        return orderId;
    }

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

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder responsible(String responsible) {
            this.responsible = responsible;
            return this;
        }

        public Builder itemName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder reportedPrice(float reportedPrice) {
            this.reportedPrice = reportedPrice;
            return this;
        }

        public Builder totalPrice(float totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder mainQuality(Quality mainQuality) {
            this.mainQuality = mainQuality;
            return this;
        }

        public Builder alternateQuality(Quality alternateQuality) {
            this.alternateQuality = alternateQuality;
            return this;
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        public Builder orderId(AppOrder orderId) {
            this.orderId = orderId;
            return this;
        }

        public OrderItem build(){
            return new OrderItem(this);
        }
    }

}
