package Hospital_Mangement.com.example.demo.repository;

import Hospital_Mangement.com.example.demo.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {
}