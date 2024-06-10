package itacademy.misbackend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import itacademy.misbackend.dto.AppointmentDto;
import itacademy.misbackend.dto.CustomResponseMessage;
import itacademy.misbackend.enums.ResultCode;
import itacademy.misbackend.enums.ResultCodeAPI;
import itacademy.misbackend.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Appointments", description = "Тут находятся все роуты связанные с записями приема")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    @PostMapping()
    public CustomResponseMessage<AppointmentDto> create (@RequestBody AppointmentDto appointmentDto) {
        return new CustomResponseMessage<>(
                appointmentService.create(appointmentDto),
                ResultCodeAPI.CREATED,
                null,
                "success",
                ResultCode.CREATED);
    }

    @GetMapping()
    public CustomResponseMessage<List<AppointmentDto>> getAll () {
        return new CustomResponseMessage<>(
                appointmentService.getAll(),
                ResultCodeAPI.SUCCESS,
                null,
                "Список всех доступных приемов получен",
                ResultCode.OK);
    }

    @GetMapping("/{id}")
    public CustomResponseMessage<AppointmentDto> getById (@PathVariable Long id) {
        return new CustomResponseMessage<>(
                appointmentService.getById(id),
                ResultCodeAPI.SUCCESS,
                null,
                "Запись приема успешно найден",
                ResultCode.OK);
    }

    @PutMapping("/{id}")
    public CustomResponseMessage<AppointmentDto> update (@PathVariable Long id, @RequestBody AppointmentDto appointmentDto) {
        return new CustomResponseMessage<>(
                appointmentService.update(id, appointmentDto),
                ResultCodeAPI.SUCCESS,
                null,
                "Запись приема успешно обновлен",
                ResultCode.OK);
    }

    @DeleteMapping("/{id}")
    public CustomResponseMessage<String> delete (@PathVariable Long id) {
        return new CustomResponseMessage<>(
                appointmentService.delete(id),
                ResultCodeAPI.SUCCESS,
                null,
                "Запись приема успешно удален",
                ResultCode.OK);
    }
}
