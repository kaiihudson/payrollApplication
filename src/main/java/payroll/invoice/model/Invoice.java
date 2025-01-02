package payroll.invoice.model;

import jakarta.persistence.*;
import payroll.order.model.AppOrder;

import java.util.List;
import java.util.Objects;

@Entity
public class Invoice {
    private @Id
            @GeneratedValue Long id;
    private InvoiceStatus invoiceStatus;
    private String loader;


    @Transient
    private List<Long> orderList;

    @OneToMany(cascade = CascadeType.ALL)
            private List<AppOrder> orders;

    Invoice () {}

    Invoice (Builder build) {
        this.invoiceStatus = build.invoiceStatus;
        this.loader = build.loader;
        this.orders = build.orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getLoader() {
        return loader;
    }

    public void setLoader(String loader) {
        this.loader = loader;
    }

    public List<AppOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<AppOrder> orders) {
        this.orders = orders;
    }

    public List<Long> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Long> orderList) {
        this.orderList = orderList;
    }

    public static class Builder {
        private Long id;
        private InvoiceStatus invoiceStatus;
        private String loader;
        private List<AppOrder> orders;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setInvoiceStatus(InvoiceStatus invoiceStatus) {
            this.invoiceStatus = invoiceStatus;
            return this;
        }

        public Builder setLoader(String loader) {
            this.loader = loader;
            return this;
        }

        public Builder setOrders(List<AppOrder> orders) {
            this.orders = orders;
            return this;
        }

        public Invoice build() {
            return new Invoice(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Invoice invoice)) return false;
        return Objects.equals(id, invoice.id) && invoiceStatus == invoice.invoiceStatus && Objects.equals(loader, invoice.loader) && Objects.equals(orders, invoice.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, invoiceStatus, loader, orders);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", invoiceStatus=" + invoiceStatus +
                ", loader='" + loader + '\'' +
                ", orders=" + orders +
                '}';
    }
}
