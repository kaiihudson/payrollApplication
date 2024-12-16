package payroll.items.service;

import payroll.items.model.Quality;

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

    public Quality getAlternativeQuality() {
        return alternativeQuality;
    }

    public String getSource() {
        return source;
    }

    public Long getOrderId() {
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
        private Quality alternativeQuality;
        private String source;
        private Long orderId;

        public OrderItemDTO build() {
            return new OrderItemDTO(this);
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setResponsible(String responsible) {
            this.responsible = responsible;
            return this;
        }

        public Builder setItemName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setReportedPrice(float reportedPrice) {
            this.reportedPrice = reportedPrice;
            return this;
        }

        public Builder setTotalPrice(float totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder setMainQuality(Quality mainQuality) {
            this.mainQuality = mainQuality;
            return this;
        }

        public Builder setAlternativeQuality(Quality alternativeQuality) {
            this.alternativeQuality = alternativeQuality;
            return this;
        }

        public Builder setSource(String source) {
            this.source = source;
            return this;
        }

        public Builder setOrderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }
    }
}
