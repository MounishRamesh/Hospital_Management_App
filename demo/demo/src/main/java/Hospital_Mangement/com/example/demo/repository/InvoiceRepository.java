package Hospital_Mangement.com.example.demo.repository;

import Hospital_Mangement.com.example.demo.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByPaymentStatusIgnoreCase(String status);
}