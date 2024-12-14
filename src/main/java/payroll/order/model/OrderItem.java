package payroll.order.model;

import jakarta.persistence.*;
import payroll.order.model.Quality;

@Entity
@Table(name = "order_items")
public class OrderItem {

    private @Id
    @GeneratedValue Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_order_id", nullable = false)
    private AppOrder orderId;
    private String responsible;
    private String itemName;
    private int quantity;
    private float reportedPrice;
    private float totalPrice;
    private Quality mainQuality;
    private Quality alternateQuality;
    private String source;
    private String purchaseOrder;

    OrderItem() {
    }

    OrderItem(
            String responsible,
            String itemName,
            int quantity,
            float reportedPrice,
            Quality mainQuality,
            Quality alternateQuality,
            String source
    ) {
        this.responsible = responsible;
        this.itemName = itemName;
        this.quantity = quantity;
        this.reportedPrice = reportedPrice;
        this.mainQuality = mainQuality;
        this.alternateQuality = alternateQuality;
        this.source = source;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return this.id;
    }

    public String getResponsible() {
        return this.responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.responsible = itemName;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getReportedPrice() {
        return this.reportedPrice;
    }

    public void setReportedPrice(float reportedPrice) {
        this.reportedPrice = reportedPrice;
    }

    public float getTotalPrice() {
        return this.quantity * this.reportedPrice;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Quality getMainQuality() {
        return this.mainQuality;
    }

    public void setMainQuality(Quality mainQuality) {
        this.mainQuality = mainQuality;
    }

    public Quality getAlternateQuality() {
        return this.alternateQuality;
    }

    public void setAlternateQuality(Quality alternateQuality) {
        this.alternateQuality = alternateQuality;
    }

}
