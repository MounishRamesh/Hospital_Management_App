package Hospital_Mangement.com.example.demo.controller;

import Hospital_Mangement.com.example.demo.services.InvoiceNotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
public class InvoiceNotificationController {

    private final InvoiceNotificationService notificationService;

    public InvoiceNotificationController(InvoiceNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // 🔹 Send invoice via email
    // POST /invoices/{id}/notify?via=email&to=example@gmail.com
    @PostMapping("/{id}/notify")
    public ResponseEntity<String> notifyInvoice(
            @PathVariable Long id,
            @RequestParam String via,
            @RequestParam String to,
            @RequestParam(required = false) String mediaUrl) {
        try {
            if (via.equalsIgnoreCase("email")) {
                notificationService.sendInvoiceByEmail(id, to);
                return ResponseEntity.ok("Invoice emailed successfully to " + to);
            } else if (via.equalsIgnoreCase("whatsapp")) {
                notificationService.sendInvoiceByWhatsApp(id, to, mediaUrl);
                return ResponseEntity.ok("Invoice sent to WhatsApp number: " + to);
            } else {
                return ResponseEntity.badRequest().body("Invalid method. Use via=email or via=whatsapp");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}