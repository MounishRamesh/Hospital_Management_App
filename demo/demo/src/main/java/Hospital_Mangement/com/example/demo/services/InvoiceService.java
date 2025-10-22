package Hospital_Mangement.com.example.demo.services;

import Hospital_Mangement.com.example.demo.model.Invoice;
import Hospital_Mangement.com.example.demo.repository.InvoiceRepository;
import Hospital_Mangement.com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id));
    }

    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public Invoice updateInvoice(Long id, Invoice updated) {
        Invoice existing = getInvoiceById(id);
        existing.setPatientName(updated.getPatientName());
        existing.setAmount(updated.getAmount());
        existing.setServiceProvided(updated.getServiceProvided());
        existing.setPaymentStatus(updated.getPaymentStatus());
        return invoiceRepository.save(existing);
    }

    public void deleteInvoice(Long id) {
        Invoice invoice = getInvoiceById(id);
        invoiceRepository.delete(invoice);
    }

    public List<Invoice> getInvoicesByStatus(String status) {
        return invoiceRepository.findByPaymentStatusIgnoreCase(status);
    }
}
