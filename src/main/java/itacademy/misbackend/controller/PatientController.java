package itacademy.misbackend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import itacademy.misbackend.dto.PatientDto;
import itacademy.misbackend.dto.ResponseMessageAPI;
import itacademy.misbackend.enums.ResultCode;
import itacademy.misbackend.enums.ResultCodeAPI;
import itacademy.misbackend.exception.NotFoundException;
import itacademy.misbackend.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Patients", description = "Тут находятся все роуты связанные с пациентами")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;

    @PostMapping()
    public ResponseMessageAPI<PatientDto> create (@RequestBody PatientDto patientDto) {
        return new ResponseMessageAPI<>(
                patientService.create(patientDto),
                ResultCodeAPI.CREATED,
                null,
                "success",
                ResultCode.CREATED.getHttpCode());
    }

    @GetMapping()
    public ResponseMessageAPI<List<PatientDto>> getAll () {
        try {
            return new ResponseMessageAPI<>(
                    patientService.getAll(),
                    ResultCodeAPI.SUCCESS,
                    null,
                    "Список всех доступных пациентов получен",
                    ResultCode.OK.getHttpCode());
        } catch (NotFoundException exception) {
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    exception.getMessage(),
                    ResultCode.NOT_FOUND.getHttpCode()
            );
        } catch (Exception e) {
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION,
                    e.getClass().getSimpleName(),
                    "Ошибка сервера",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseMessageAPI<PatientDto> getById (@PathVariable Long id) {
        try {
            return new ResponseMessageAPI<>(
                    patientService.getById(id),
                    ResultCodeAPI.SUCCESS,
                    null,
                    "Пациент успешно найден",
                    ResultCode.OK.getHttpCode());
        } catch (NotFoundException exception) {
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.FAIL,
                    exception.getClass().getSimpleName(),
                    exception.getMessage(),
                    ResultCode.NOT_FOUND.getHttpCode()
            );
        } catch (Exception e) {
            return new ResponseMessageAPI<>(
                    null,
                    ResultCodeAPI.EXCEPTION,
                    e.getClass().getSimpleName(),
                    "Ошибка сервера",
                    ResultCode.FAIL.getHttpCode()
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseMessageAPI<PatientDto> update (@PathVariable Long id, @RequestBody PatientDto patientDto) {
        return new ResponseMessageAPI<>(
                patientService.update(id, patientDto),
                ResultCodeAPI.SUCCESS,
                null,
                "Пациент успешно обновлен",
                ResultCode.OK.getHttpCode());
    }

    @DeleteMapping("/{id}")
    public ResponseMessageAPI<String> delete (@PathVariable Long id) {
        return new ResponseMessageAPI<>(
                patientService.delete(id),
                ResultCodeAPI.SUCCESS,
                null,
                "Пациент успешно удален",
                ResultCode.OK.getHttpCode());
    }
}
