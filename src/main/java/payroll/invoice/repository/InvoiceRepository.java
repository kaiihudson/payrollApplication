package payroll.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import payroll.invoice.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
