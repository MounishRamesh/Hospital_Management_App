package Hospital_Mangement.com.example.demo.controller;


import Hospital_Mangement.com.example.demo.model.Appointment;
import Hospital_Mangement.com.example.demo.services.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    public AppointmentController(AppointmentService appointmentService) { this.appointmentService = appointmentService; }

    @GetMapping
    public List<Appointment> getAllAppointments() { return appointmentService.getAllAppointments(); }

    @PostMapping
    public Appointment addAppointment(@RequestBody Appointment appointment) { return appointmentService.saveAppointment(appointment); }

    @DeleteMapping("/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return "Appointment deleted with id: " + id;
    }
}
