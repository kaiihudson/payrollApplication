package payroll.invoice.controller;


import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payroll.invoice.model.Invoice;
import payroll.invoice.service.InvoiceDTO;
import payroll.invoice.service.InvoiceMapper;
import payroll.invoice.service.InvoiceService;
import payroll.order.model.OrderUpdate;
import payroll.order.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InvoiceController {
    private final InvoiceService invoiceService;
    private final OrderService orderService;
    private final InvoiceMapper invoiceMapper;

    public InvoiceController(InvoiceService invoiceService, OrderService orderService, InvoiceMapper invoiceMapper) {
        this.invoiceService = invoiceService;
        this.orderService = orderService;
        this.invoiceMapper = invoiceMapper;
    }

    @GetMapping("/invoices")
    public ResponseEntity<List<InvoiceDTO>> all() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @PostMapping("/invoices")
    public HttpEntity<InvoiceDTO> postInvoice(@RequestBody Invoice invoice){
        orderService.updateList(invoiceMapper.mapInvoiceToOrderUpdate(invoice));
        return ResponseEntity.ok(invoiceService.createInvoice(invoice));
    }
}
