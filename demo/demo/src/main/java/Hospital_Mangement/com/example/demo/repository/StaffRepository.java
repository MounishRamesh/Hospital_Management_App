package Hospital_Mangement.com.example.demo.repository;

import Hospital_Mangement.com.example.demo.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findByRoleIgnoreCase(String role);
}
