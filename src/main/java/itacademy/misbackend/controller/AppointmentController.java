package itacademy.misbackend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import itacademy.misbackend.dto.AppointmentDto;
import itacademy.misbackend.dto.ResponseMessageAPI;
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
    public ResponseMessageAPI<AppointmentDto> create (@RequestBody AppointmentDto appointmentDto) {
        return new ResponseMessageAPI<>(
                appointmentService.create(appointmentDto),
                ResultCodeAPI.CREATED,
                null,
                "success",
                ResultCode.CREATED.getHttpCode());
    }

    @GetMapping()
    public ResponseMessageAPI<List<AppointmentDto>> getAll () {
        return new ResponseMessageAPI<>(
                appointmentService.getAll(),
                ResultCodeAPI.SUCCESS,
                null,
                "Список всех доступных приемов получен",
                ResultCode.OK.getHttpCode());
    }

    @GetMapping("/{id}")
    public ResponseMessageAPI<AppointmentDto> getById (@PathVariable Long id) {
        return new ResponseMessageAPI<>(
                appointmentService.getById(id),
                ResultCodeAPI.SUCCESS,
                null,
                "Запись приема успешно найден",
                ResultCode.OK.getHttpCode());
    }

    @PutMapping("/{id}")
    public ResponseMessageAPI<AppointmentDto> update (@PathVariable Long id, @RequestBody AppointmentDto appointmentDto) {
        return new ResponseMessageAPI<>(
                appointmentService.update(id, appointmentDto),
                ResultCodeAPI.SUCCESS,
                null,
                "Запись приема успешно обновлен",
                ResultCode.OK.getHttpCode());
    }

    @DeleteMapping("/{id}")
    public ResponseMessageAPI<String> delete (@PathVariable Long id) {
        return new ResponseMessageAPI<>(
                appointmentService.delete(id),
                ResultCodeAPI.SUCCESS,
                null,
                "Запись приема успешно удален",
                ResultCode.OK.getHttpCode());
    }
}
