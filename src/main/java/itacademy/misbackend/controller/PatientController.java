package itacademy.misbackend.controller;

import itacademy.misbackend.dto.PatientDto;
import itacademy.misbackend.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;

    @PostMapping()
    public ResponseEntity<PatientDto> create (@RequestBody PatientDto patientDto) {
        PatientDto createdPatient = patientService.create(patientDto);
        if (createdPatient == null) {
            return ResponseEntity.badRequest().build();
            //Временная мера, пока не реализована обработка ошибок
        }
        return ResponseEntity.ok(createdPatient);
    }

    @GetMapping()
    public ResponseEntity<List<PatientDto>> getAll () {
        var patients = patientService.getAll();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getById (@PathVariable Long id) {
        PatientDto patient = patientService.getById(id);
        if (patient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> update (@PathVariable Long id, @RequestBody PatientDto patientDto) {
        PatientDto updatedPatient = patientService.update(id, patientDto);
        if (updatedPatient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id) {
        String result = patientService.delete(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
