package Hospital_Mangement.com.example.demo.services;

import Hospital_Mangement.com.example.demo.exception.ResourceNotFoundException;
import Hospital_Mangement.com.example.demo.model.Billing;
import Hospital_Mangement.com.example.demo.repository.BillingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingService {

    private final BillingRepository billingRepository;

    public BillingService(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }

    public List<Billing> getAllBills() {
        return billingRepository.findAll();
    }

    public Billing getBillById(Long id) {
        return billingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found with id: " + id));
    }

    public Billing createBill(Billing bill) {
        return billingRepository.save(bill);
    }

    public Billing updateBill(Long id, Billing updatedBill) {
        Billing existing = getBillById(id);
        existing.setPatientName(updatedBill.getPatientName());
        existing.setAmount(updatedBill.getAmount());
        existing.setServiceType(updatedBill.getServiceType());
        existing.setPaymentStatus(updatedBill.getPaymentStatus());
        return billingRepository.save(existing);
    }

    public void deleteBill(Long id) {
        Billing existing = getBillById(id);
        billingRepository.delete(existing);
    }
}