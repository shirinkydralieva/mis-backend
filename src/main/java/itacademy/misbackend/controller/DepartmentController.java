package itacademy.misbackend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import itacademy.misbackend.dto.DepartmentDto;
import itacademy.misbackend.dto.ResponseMessageAPI;
import itacademy.misbackend.enums.ResultCode;
import itacademy.misbackend.enums.ResultCodeAPI;
import itacademy.misbackend.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Departments", description = "Тут находятся все роуты связанные с отделениями")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping()
    public ResponseMessageAPI<DepartmentDto> create (@RequestBody DepartmentDto departmentDto) {
        return new ResponseMessageAPI<>(
                departmentService.create(departmentDto),
                ResultCodeAPI.CREATED,
                null,
                "success",
                ResultCode.CREATED.getHttpCode());
    }

    @GetMapping()
    public ResponseMessageAPI<List<DepartmentDto>> getAll () {
        return new ResponseMessageAPI<>(
                departmentService.getAll(),
                ResultCodeAPI.SUCCESS,
                null,
                "Список всех доступных отделений получен",
                ResultCode.OK.getHttpCode());
    }

    @GetMapping("/{id}")
    public ResponseMessageAPI<DepartmentDto> getById (@PathVariable Long id) {
        return new ResponseMessageAPI<>(
                departmentService.getById(id),
                ResultCodeAPI.SUCCESS,
                null,
                "Отделение успешно найдено",
                ResultCode.OK.getHttpCode());
    }

    @PutMapping("/{id}")
    public ResponseMessageAPI<DepartmentDto> update (@PathVariable Long id, @RequestBody DepartmentDto departmentDto) {
        return new ResponseMessageAPI<>(
                departmentService.update(id, departmentDto),
                ResultCodeAPI.SUCCESS,
                null,
                "Отделение успешно обновлено",
                ResultCode.OK.getHttpCode());
    }

    @DeleteMapping("/{id}")
    public ResponseMessageAPI<String> delete (@PathVariable Long id) {
        return new ResponseMessageAPI<>(
                departmentService.delete(id),
                ResultCodeAPI.SUCCESS,
                null,
                "Отделение успешно удалено",
                ResultCode.OK.getHttpCode());
    }
}
