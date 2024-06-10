package itacademy.misbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import itacademy.misbackend.dto.MedicalRecordDto;
import itacademy.misbackend.dto.ResponseMessageAPI;
import itacademy.misbackend.enums.ResultCode;
import itacademy.misbackend.enums.ResultCodeAPI;
import itacademy.misbackend.exception.NotFoundException;
import itacademy.misbackend.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Medical Records", description = "Тут находятся все роуты связанные с мед записями приема")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medicalRecords")
public class MedicalRecordController {
    private final MedicalRecordService service;

//Обработка ошибок
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Мед запись успешно создана.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Не удалось добавить мед запись.")
    })
    @Operation(summary = "Этот роут для создание мед записей.")
    @PostMapping
    public ResponseMessageAPI<MedicalRecordDto> create(@RequestBody MedicalRecordDto recordDto) {
        try {
            return new ResponseMessageAPI<>(
                    service.create(recordDto),
                    ResultCodeAPI.CREATED,
                    null,
                    "success",
                    ResultCode.CREATED.getHttpCode()
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

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Мед запись по id успешно найден.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Мед запись по этой id не найден."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Не удалось выполнить поиск.")
    })
    @Operation(summary = "Этот роут для поиска мед записей по id.")
    @GetMapping("/{id}")
    public ResponseMessageAPI<MedicalRecordDto> getById(@PathVariable Long id) {
            return new ResponseMessageAPI<>(
                    service.getById(id),
                    ResultCodeAPI.CREATED,
                    null,
                    "success",
                    ResultCode.CREATED.getHttpCode()
            );
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все доступные мед записи получены",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = MedicalRecordDto.class)))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Мед записей нет"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Не удалось выполнить поиск.")
    })
    @Operation(summary = "Этот роут возвращает все доступные мед записи")
    @GetMapping()
    public ResponseMessageAPI<List<MedicalRecordDto>> getAll() {
            try {
                return new ResponseMessageAPI<>(
                        service.getAll(),
                        ResultCodeAPI.SUCCESS,
                        null,
                        "Все доступные мед записи успешно получены",
                        ResultCode.OK.getHttpCode()
                );
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

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Мед запись успешно обновлена",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MedicalRecordDto.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Мед запись не найдена"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Не удалось обновить мед запись.")
    })
    @Operation(summary = "Этот роут выполняет поиск мед записи по id и обновляет")
    @PutMapping("/update/{id}")
    public ResponseMessageAPI<MedicalRecordDto> update(@PathVariable Long id, @RequestBody MedicalRecordDto recordDto) {
        return new ResponseMessageAPI<>(
                service.update(id, recordDto),
                ResultCodeAPI.SUCCESS,
                null,
                "success",
                ResultCode.OK.getHttpCode()
        );
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Мед запись успешно удалена",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MedicalRecordDto.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Мед запись не найдена"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Не удалось удалить мед запись.")
    })
    @Operation(summary = "Этот роут удаляет мед запись по id")
    @PutMapping("/delete/{id}")
    public ResponseMessageAPI<String> delete(@PathVariable Long id) {
            return new ResponseMessageAPI<>(
                    service.delete(id),
                    ResultCodeAPI.SUCCESS,
                    null,
                    "success",
                    ResultCode.OK.getHttpCode()
            );
    }

}
