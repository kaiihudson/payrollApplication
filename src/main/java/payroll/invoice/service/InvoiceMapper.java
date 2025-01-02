package payroll.invoice.service;


import org.springframework.stereotype.Component;
import payroll.invoice.model.Invoice;
import payroll.order.model.AppOrder;
import payroll.order.model.OrderUpdate;

import java.util.ArrayList;
import java.util.List;

@Component
public class InvoiceMapper {
    private List<Long> extractOrderIds(List<AppOrder> orders) {
        List<Long> ids = new ArrayList<>();
        for (AppOrder order : orders) {
            Long id = order.getId();
            ids.add(id);
        }
        return ids;
    }
    public InvoiceDTO mapToDTO(Invoice invoice) {
        return new InvoiceDTO.Builder()
                .setId(invoice.getId())
                .setLoader(invoice.getLoader())
                .setOrderList(this.extractOrderIds(invoice.getOrders()))
                .setStatus(invoice.getInvoiceStatus())
                .build();
    }
    public InvoiceDTO addTotal(InvoiceDTO invoice, Float total) {
        invoice.setInvoiceTotal(total);
        return invoice;
    }
    public OrderUpdate mapInvoiceToOrderUpdate(Invoice invoice) {
        List<Long> orderList= invoice.getOrderList();
        return new OrderUpdate.Builder().setIdList(orderList).build();
    }
}
