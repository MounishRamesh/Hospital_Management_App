package Hospital_Mangement.com.example.demo.services;

import Hospital_Mangement.com.example.demo.model.Doctor;
import Hospital_Mangement.com.example.demo.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    public DoctorService(DoctorRepository doctorRepository) { this.doctorRepository = doctorRepository; }

    public List<Doctor> getAllDoctors() { return doctorRepository.findAll(); }
    public Doctor saveDoctor(Doctor doctor) { return doctorRepository.save(doctor); }
    public void deleteDoctor(Long id) { doctorRepository.deleteById(id); }
}
