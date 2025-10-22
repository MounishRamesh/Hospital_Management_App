package Hospital_Mangement.com.example.demo.controller;

import com.hospital.model.EHR;
import com.hospital.repository.EHRRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ehr")
@CrossOrigin
public class EHRController {

    private final EHRRepository repository;

    public EHRController(EHRRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<EHR> getAllRecords() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public EHR getRecord(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("EHR not found"));
    }

    @PostMapping
    public EHR addRecord(@RequestBody EHR ehr) {
        return repository.save(ehr);
    }

    @PutMapping("/{id}")
    public EHR updateRecord(@PathVariable Long id, @RequestBody EHR ehr) {
        ehr.setId(id);
        return repository.save(ehr);
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        repository.deleteById(id);
    }
}