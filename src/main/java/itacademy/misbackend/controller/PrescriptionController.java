package itacademy.misbackend.controller;

import itacademy.misbackend.dto.PrescriptionDto;
import itacademy.misbackend.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/prescription")
public class PrescriptionController {

    private final PrescriptionService service;

    @PostMapping
    public ResponseEntity<PrescriptionDto> create(@RequestBody PrescriptionDto dto) {
        try {
            return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDto> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<PrescriptionDto>> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<PrescriptionDto> update(@RequestParam Long id, @RequestBody PrescriptionDto dto) {
        try {
            return new ResponseEntity<>(service.update(id, dto),HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
