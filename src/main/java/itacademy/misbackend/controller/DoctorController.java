package itacademy.misbackend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import itacademy.misbackend.dto.DoctorDto;
import itacademy.misbackend.dto.ResponseMessageAPI;
import itacademy.misbackend.enums.ResultCode;
import itacademy.misbackend.enums.ResultCodeAPI;
import itacademy.misbackend.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Doctors", description = "Тут находятся все роуты связанные с врачами")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping()
    public ResponseMessageAPI<DoctorDto> create (@RequestBody DoctorDto doctorDto) {
        return new ResponseMessageAPI<>(
                doctorService.create(doctorDto),
                ResultCodeAPI.CREATED,
                null,
                "success",
                ResultCode.CREATED.getHttpCode());
    }

    @GetMapping()
    public ResponseMessageAPI<List<DoctorDto>> getAll () {
        return new ResponseMessageAPI<>(
                doctorService.getAll(),
                ResultCodeAPI.SUCCESS,
                null,
                "Список всех доступных врачей получен",
                ResultCode.OK.getHttpCode());
    }

    @GetMapping("/{id}")
    public ResponseMessageAPI<DoctorDto> getById (@PathVariable Long id) {
        return new ResponseMessageAPI<>(
                doctorService.getById(id),
                ResultCodeAPI.SUCCESS,
                null,
                "Врач успешно найден",
                ResultCode.OK.getHttpCode());
    }

    @PutMapping("/{id}")
    public ResponseMessageAPI<DoctorDto> update (@PathVariable Long id, @RequestBody DoctorDto doctorDto) {
        return new ResponseMessageAPI<>(
                doctorService.update(id, doctorDto),
                ResultCodeAPI.SUCCESS,
                null,
                "Врач успешно обновлен",
                ResultCode.OK.getHttpCode());
    }

    @DeleteMapping("/{id}")
    public ResponseMessageAPI<String> delete (@PathVariable Long id) {
        return new ResponseMessageAPI<>(
                doctorService.delete(id),
                ResultCodeAPI.SUCCESS,
                null,
                "Врач успешно удален",
                ResultCode.OK.getHttpCode());
    }
}
