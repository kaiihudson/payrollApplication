package payroll.invoice.service;

import org.springframework.stereotype.Service;
import payroll.invoice.model.Invoice;
import payroll.invoice.model.InvoiceStatus;
import payroll.invoice.repository.InvoiceRepository;
import payroll.items.repository.OrderItemRepository;
import payroll.order.model.AppOrder;
import payroll.order.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final InvoiceMapper invoiceMapper;

    public InvoiceService(InvoiceRepository invoiceRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository, InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.invoiceMapper = invoiceMapper;
    }

    public List<InvoiceDTO> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream().map(invoiceMapper::mapToDTO).collect(Collectors.toList());
    }


    private Float totalOfInvoice(List<AppOrder> orders) {
        Float initial = 0.0F;
        for(AppOrder order: orders) {
           Float orderTotal = orderItemRepository.sumTotalPriceByOrderId(order);
           initial = initial + orderTotal;
        }
        return initial;
    }

    public InvoiceDTO createInvoice(Invoice invoice) {
        List<Long> ids = invoice.getOrderList();
        List<AppOrder> orders = orderRepository.findByIdIn(ids);
        Invoice newInvoice =  invoiceRepository.save(
                new Invoice.Builder()
                        .setInvoiceStatus(InvoiceStatus.CREATED)
                        .setLoader(invoice.getLoader())
                        .setOrders(orders)
                        .build()
        );
        InvoiceDTO invoiceDTO = invoiceMapper.mapToDTO(newInvoice);
        Float total = totalOfInvoice(orders);
        return invoiceMapper.addTotal(invoiceDTO, total);
    }
}
