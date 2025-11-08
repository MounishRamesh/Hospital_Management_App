package Hospital_Mangement.com.example.demo.controller;

import Hospital_Mangement.com.example.demo.model.Billing;
import Hospital_Mangement.com.example.demo.services.BillingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billing")
public class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @GetMapping
    public ResponseEntity<List<Billing>> getAllBills() {
        return ResponseEntity.ok(billingService.getAllBills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Billing> getBillById(@PathVariable Long id) {
        return ResponseEntity.ok(billingService.getBillById(id));
    }

    @PostMapping
    public ResponseEntity<Billing> createBill(@Valid @RequestBody Billing bill) {
        Billing created = billingService.createBill(bill);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Billing> updateBill(@PathVariable Long id, @Valid @RequestBody Billing bill) {
        Billing updated = billingService.updateBill(id, bill);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBill(@PathVariable Long id) {
        billingService.deleteBill(id);
        return ResponseEntity.ok("Bill deleted with id: " + id);
    }
}
