package itacademy.misbackend.controller;

import itacademy.misbackend.dto.AppointmentDto;
import itacademy.misbackend.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    @PostMapping()
    public ResponseEntity<AppointmentDto> create (@RequestBody AppointmentDto appointmentDto) {
        AppointmentDto createdAppointment = appointmentService.create(appointmentDto);
        if (createdAppointment == null) {
            return ResponseEntity.badRequest().build();
            //Временная мера, пока не реализована обработка ошибок
        }
        return ResponseEntity.ok(createdAppointment);
    }

    @GetMapping()
    public ResponseEntity<List<AppointmentDto>> getAll () {
        var appointments = appointmentService.getAll();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> getById (@PathVariable Long id) {
        AppointmentDto appointment = appointmentService.getById(id);
        if (appointment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(appointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDto> update (@PathVariable Long id, @RequestBody AppointmentDto appointmentDto) {
        AppointmentDto updatedAppointment = appointmentService.update(id, appointmentDto);
        if (updatedAppointment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAppointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id) {
        String result = appointmentService.delete(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
