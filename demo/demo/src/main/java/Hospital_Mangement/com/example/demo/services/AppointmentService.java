package Hospital_Mangement.com.example.demo.services;

import Hospital_Mangement.com.example.demo.model.Appointment;
import Hospital_Mangement.com.example.demo.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    public AppointmentService(AppointmentRepository appointmentRepository) { this.appointmentRepository = appointmentRepository; }

    public List<Appointment> getAllAppointments() { return appointmentRepository.findAll(); }
    public Appointment saveAppointment(Appointment appointment) { return appointmentRepository.save(appointment); }
    public void deleteAppointment(Long id) { appointmentRepository.deleteById(id); }
}
