package Hospital_Mangement.com.example.demo.model;


import jakarta.persistence.*;

@Entity
@Table (name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    private String name;
    private String specialty;
    private String contact;

    public Doctor() {}
    public Doctor(String name, String specialty, String contact) {
        this.name = name;
        this.specialty = specialty;
        this.contact = contact;
    }

    // Getters and Setters
    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

}
