package itacademy.misbackend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import itacademy.misbackend.dto.CustomResponseMessage;
import itacademy.misbackend.dto.DoctorDto;
import itacademy.misbackend.dto.CustomResponseMessage;
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
    public CustomResponseMessage<DoctorDto> create (@RequestBody DoctorDto doctorDto) {
        return new CustomResponseMessage<>(
                doctorService.save(doctorDto),
                ResultCodeAPI.CREATED,
                null,
                "success",
                ResultCode.CREATED);
    }

    @GetMapping()
    public CustomResponseMessage<List<DoctorDto>> getAll () {
        return new CustomResponseMessage<>(
                doctorService.getAll(),
                ResultCodeAPI.SUCCESS,
                null,
                "Список всех доступных врачей получен",
                ResultCode.OK);
    }

    @GetMapping("/{id}")
    public CustomResponseMessage<DoctorDto> getById (@PathVariable Long id) {
        return new CustomResponseMessage<>(
                doctorService.getById(id),
                ResultCodeAPI.SUCCESS,
                null,
                "Врач успешно найден",
                ResultCode.OK);
    }

    @PutMapping("/{id}")
    public CustomResponseMessage<DoctorDto> update (@PathVariable Long id, @RequestBody DoctorDto doctorDto) {
        return new CustomResponseMessage<>(
                doctorService.update(id, doctorDto),
                ResultCodeAPI.SUCCESS,
                null,
                "Врач успешно обновлен",
                ResultCode.OK);
    }

    @DeleteMapping("/{id}")
    public CustomResponseMessage<String> delete (@PathVariable Long id) {
        return new CustomResponseMessage<>(
                doctorService.delete(id),
                ResultCodeAPI.SUCCESS,
                null,
                "Врач успешно удален",
                ResultCode.OK);
    }
}
