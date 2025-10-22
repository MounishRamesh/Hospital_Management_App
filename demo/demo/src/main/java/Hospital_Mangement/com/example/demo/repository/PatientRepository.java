package Hospital_Mangement.com.example.demo.repository;

import Hospital_Mangement.com.example.demo.model.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends JpaRepository<Patient , Long> {

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE patients AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement() ;
}
