package Hospital_Mangement.com.example.demo.services;

import Hospital_Mangement.com.example.demo.exception.ResourceNotFoundException;
import Hospital_Mangement.com.example.demo.model.Staff;
import Hospital_Mangement.com.example.demo.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StaffService {
    private final StaffRepository staffRepository;
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Staff getStaffById(Long id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
    }

    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff updateStaff(Long id, Staff updated) {
        Staff existing = getStaffById(id);
        existing.setName(updated.getName());
        existing.setRole(updated.getRole());
        existing.setContact(updated.getContact());
        existing.setEmail(updated.getEmail());
        existing.setShiftStart(updated.getShiftStart());
        existing.setShiftEnd(updated.getShiftEnd());
        return staffRepository.save(existing);
    }

    public void deleteStaff(Long id) {
        Staff s = getStaffById(id);
        staffRepository.delete(s);
    }

    public List<Staff> findByRole(String role) {
        return staffRepository.findByRoleIgnoreCase(role);
    }
}
