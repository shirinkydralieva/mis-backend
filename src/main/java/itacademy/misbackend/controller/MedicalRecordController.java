package itacademy.misbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import itacademy.misbackend.dto.MedicalRecordDto;
import itacademy.misbackend.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medicalRecords")
public class MedicalRecordController {
    private final MedicalRecordService service;


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
    public ResponseEntity<MedicalRecordDto> create(@RequestBody MedicalRecordDto recordDto) {
      //  try {
            return new ResponseEntity<>(service.create(recordDto),HttpStatus.CREATED);
    //    } catch (NullPointerException e) {
     //       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     //   } catch (Exception e) {
     //       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

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
    public ResponseEntity<MedicalRecordDto> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
    public ResponseEntity<List<MedicalRecordDto>> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Мед запись найдена и успешно изменена",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MedicalRecordDto.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Мед запись не найдена"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Не удалось изменить Мед запись.")
    })
    @Operation(summary = "Этот роут выполняет поиск Мед записи по id и обновляет")
    @PutMapping("/update")
    public ResponseEntity<MedicalRecordDto> update(@RequestParam Long id, @RequestBody MedicalRecordDto recordDto) {
        try {
            return new ResponseEntity<>(service.update(id, recordDto),HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
