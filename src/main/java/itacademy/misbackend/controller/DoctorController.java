package itacademy.misbackend.controller;

import itacademy.misbackend.dto.DoctorDto;
import itacademy.misbackend.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping()
    public ResponseEntity<DoctorDto> create (@RequestBody DoctorDto doctorDto) {
        DoctorDto createdDoctor = doctorService.save(doctorDto);
        if (createdDoctor == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdDoctor);
    }

    @GetMapping()
    public ResponseEntity<List<DoctorDto>> getAll () {
        var doctors = doctorService.getAll();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> getById (@PathVariable Long id) {
        DoctorDto doctor = doctorService.getById(id);
        if (doctor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(doctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDto> update (@PathVariable Long id, @RequestBody DoctorDto doctorDto) {
        DoctorDto updatedDoctor = doctorService.update(id, doctorDto);
        if (updatedDoctor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id) {
        String result = doctorService.delete(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
