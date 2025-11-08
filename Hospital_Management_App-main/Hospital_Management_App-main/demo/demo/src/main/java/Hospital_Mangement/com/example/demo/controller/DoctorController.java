package Hospital_Mangement.com.example.demo.controller;


import Hospital_Mangement.com.example.demo.model.Doctor;
import Hospital_Mangement.com.example.demo.services.DoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    public DoctorController(DoctorService doctorService) { this.doctorService = doctorService; }

    @GetMapping
    public List<Doctor> getAllDoctors() { return doctorService.getAllDoctors(); }

    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) { return doctorService.saveDoctor(doctor); }

    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "Doctor deleted with id: " + id;
    }
}
