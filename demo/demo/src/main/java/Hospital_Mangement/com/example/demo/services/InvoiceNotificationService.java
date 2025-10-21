package Hospital_Mangement.com.example.demo.services;

import Hospital_Mangement.com.example.demo.model.Invoice;
import org.springframework.stereotype.Service;

@Service
public class InvoiceNotificationService {

    private final InvoiceService invoiceService;
    private final InvoicePdfService pdfService;
    private final EmailService emailService;
    private final WhatsappService whatsappService;

    public InvoiceNotificationService(InvoiceService invoiceService,
                                      InvoicePdfService pdfService,
                                      EmailService emailService,
                                      WhatsappService whatsappService) {
        this.invoiceService = invoiceService;
        this.pdfService = pdfService;
        this.emailService = emailService;
        this.whatsappService = whatsappService;
    }

    public void sendInvoiceByEmail(Long invoiceId, String email) {
        Invoice invoice = invoiceService.getInvoiceById(invoiceId);
        byte[] pdf = pdfService.generateInvoicePdf(invoice);

        String filename = "invoice_" + invoiceId + ".pdf";
        String subject = "Your Invoice #" + invoiceId;
        String body = "Dear " + invoice.getPatientName() + ",\n\nPlease find your invoice attached.\n\nThank you.";

        emailService.sendEmailWithAttachment(email, subject, body, pdf, filename);
    }

    public void sendInvoiceByWhatsApp(Long invoiceId, String whatsappNumber, String mediaUrl) {
        Invoice invoice = invoiceService.getInvoiceById(invoiceId);
        String message = "Invoice #" + invoice.getId() + "\nService: " + invoice.getServiceProvided()
                + "\nAmount: ₹" + invoice.getAmount() + "\nStatus: " + invoice.getPaymentStatus();

        if (mediaUrl != null && !mediaUrl.isEmpty())
            whatsappService.sendWhatsappMedia(whatsappNumber, message, mediaUrl);
        else
            whatsappService.sendWhatsappText(whatsappNumber, message);
    }
}
