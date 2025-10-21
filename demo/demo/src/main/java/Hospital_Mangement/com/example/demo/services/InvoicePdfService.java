package Hospital_Mangement.com.example.demo.services;

import Hospital_Mangement.com.example.demo.model.Invoice;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.util.Locale;

@Service
public class InvoicePdfService {

    public byte[] generateInvoicePdf(Invoice invoice) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("INVOICE").setBold().setFontSize(18));
            document.add(new Paragraph("Invoice ID: " + invoice.getId()));
            document.add(new Paragraph("Patient Name: " + invoice.getPatientName()));
            document.add(new Paragraph("Service Provided: " + invoice.getServiceProvided()));

            NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
            String amount = format.format(invoice.getAmount());
            document.add(new Paragraph("Amount: " + amount));
            document.add(new Paragraph("Payment Status: " + invoice.getPaymentStatus()));

            document.add(new Paragraph("\nThank you for visiting our hospital."));
            document.close();

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}