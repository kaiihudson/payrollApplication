package payroll.invoice.service;

import payroll.invoice.model.InvoiceStatus;

import java.util.List;

public class InvoiceDTO {
    private Long id;
    private String loader;
    private InvoiceStatus status;
    private List<Long> orderList;
    private Float invoiceTotal;

    InvoiceDTO(Builder builder ) {
        this.id = builder.id;
        this.loader = builder.loader;
        this.status = builder.status;
        this.orderList = builder.orderList;
        this.invoiceTotal = builder.invoiceTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoader() {
        return loader;
    }

    public void setLoader(String loader) {
        this.loader = loader;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public List<Long> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Long> orderList) {
        this.orderList = orderList;
    }

    public Float getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(Float invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public static class Builder {
        private Long id;
        private String loader;
        private InvoiceStatus status;
        private List<Long> orderList;
        private Float invoiceTotal;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setInvoiceTotal(float total) {
            this.invoiceTotal = total;
            return this;
        }

        public Builder setLoader(String loader) {
            this.loader = loader;
            return this;
        }

        public Builder setStatus(InvoiceStatus status) {
            this.status = status;
            return this;
        }

        public Builder setOrderList(List<Long> orderList) {
            this.orderList = orderList;
            return this;
        }

        public InvoiceDTO build() {
            return new InvoiceDTO(this);
        }
    }
}
