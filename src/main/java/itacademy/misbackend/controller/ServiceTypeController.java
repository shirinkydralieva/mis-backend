package itacademy.misbackend.controller;

import itacademy.misbackend.dto.ServiceTypeDto;
import itacademy.misbackend.service.ServiceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/services")
public class ServiceTypeController {

    private final ServiceTypeService service;

    @PostMapping
    public ResponseEntity<ServiceTypeDto> create(@RequestBody ServiceTypeDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<List<ServiceTypeDto>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceTypeDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ServiceTypeDto> update(@RequestParam Long id, @RequestBody ServiceTypeDto dto) {
            return new ResponseEntity<>(service.update(id, dto),HttpStatus.OK);
    }

    @PutMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) {
            return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
    }

}
