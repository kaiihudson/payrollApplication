package payroll.items.service;

import payroll.items.model.Quality;
import payroll.items.model.ItemsStatus;

public class OrderItemDTO {
    private Long id;
    private String responsible;
    private String itemName;
    private int quantity;
    private Float reportedPrice;
    private Float totalPrice;
    private Quality mainQuality;
    private Quality alternateQuality;
    private String source;
    private Long orderId;
    private ItemsStatus itemItemsStatus;
    private String retailer;

    public OrderItemDTO(Builder build) {
        this.id = build.id;
        this.responsible = build.responsible;
        this.itemName = build.itemName;
        this.quantity = build.quantity;
        this.reportedPrice = build.reportedPrice;
        this.totalPrice = build.totalPrice;
        this.mainQuality = build.mainQuality;
        this.alternateQuality = build.alternateQuality;
        this.source = build.source;
        this.orderId = build.orderId;
        this.itemItemsStatus = build.itemItemsStatus;
        this.retailer = build.retailer;
    }

    public String getRetailer() {
        return retailer;
    }

    public ItemsStatus getItemItemsStatus() {
        return itemItemsStatus;
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

    public Float getReportedPrice() {
        return reportedPrice;
    }

    public Float getTotalPrice() {
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

    public Long getOrderId() {
        return orderId;
    }

    public ItemsStatus getItemStatus() {
        return itemItemsStatus;
    }

    public static class Builder {
        private Long id;
        private String responsible;
        private String itemName;
        private int quantity;
        private Float reportedPrice;
        private Float totalPrice;
        private Quality mainQuality;
        private Quality alternateQuality;
        private String source;
        private Long orderId;
        private ItemsStatus itemItemsStatus;
        private String retailer;

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

        public Builder setAlternateQuality(Quality alternateQuality) {
            this.alternateQuality = alternateQuality;
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

        public Builder setItemStatus(ItemsStatus itemsStatus) {
            this.itemItemsStatus = itemsStatus;
            return this;
        }

        public Builder setRetailer(String retailer) {
            this.retailer = retailer;
            return this;
        }
    }
}
