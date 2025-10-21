package Hospital_Mangement.com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffId;

    @NotBlank
    @Size(max = 100)
    private String name;

    // Example roles: "NURSE", "RECEPTIONIST", "TECHNICIAN", "ADMIN"
    @NotBlank
    @Size(max = 50)
    private String role;

    @Size(max = 15)
    private String contact;

    @Email
    @Size(max = 100)
    private String email;

    // shift start and end time (optional)
    private LocalTime shiftStart;
    private LocalTime shiftEnd;

    public Staff() {}

    public Staff(String name, String role, String contact, String email, LocalTime shiftStart, LocalTime shiftEnd) {
        this.name = name;
        this.role = role;
        this.contact = contact;
        this.email = email;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
    }

    // Getters and setters
    public Long getStaffId() { return staffId; }
    public void setStaffId(Long staffId) { this.staffId = staffId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalTime getShiftStart() { return shiftStart; }
    public void setShiftStart(LocalTime shiftStart) { this.shiftStart = shiftStart; }
    public LocalTime getShiftEnd() { return shiftEnd; }
    public void setShiftEnd(LocalTime shiftEnd) { this.shiftEnd = shiftEnd; }
}
