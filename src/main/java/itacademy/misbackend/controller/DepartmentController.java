package itacademy.misbackend.controller;

import itacademy.misbackend.dto.DepartmentDto;
import itacademy.misbackend.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping()
    public ResponseEntity<DepartmentDto> create (@RequestBody DepartmentDto departmentDto) {
        DepartmentDto createdDepartment = departmentService.create(departmentDto);
        if (createdDepartment == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            //Временная мера, пока не реализована обработка ошибок
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<DepartmentDto>> getAll () {
        var departments = departmentService.getAll();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getById (@PathVariable Long id) {
        DepartmentDto department = departmentService.getById(id);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> update (@PathVariable Long id, @RequestBody DepartmentDto departmentDto) {
        DepartmentDto updatedDepartment = departmentService.update(id, departmentDto);
        if (updatedDepartment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable Long id) {
        String result = departmentService.delete(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
