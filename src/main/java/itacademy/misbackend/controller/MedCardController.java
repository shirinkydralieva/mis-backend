package itacademy.misbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import itacademy.misbackend.dto.MedCardDto;
import itacademy.misbackend.dto.MedicalRecordDto;
import itacademy.misbackend.dto.ResponseMessageAPI;
import itacademy.misbackend.enums.ResultCode;
import itacademy.misbackend.enums.ResultCodeAPI;
import itacademy.misbackend.service.MedCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Medical cards", description = "Тут находятся все роуты связанные с мед картами пациентов")

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medCards")
public class MedCardController {
    private final MedCardService service;

    //Обработка ошибок
    //Пока здесь только методы getAll(), getById(id), delete(id)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все доступные мед карты получены",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = MedicalRecordDto.class)))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Мед карт нет"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Не удалось выполнить поиск.")
    })
    @Operation(summary = "Этот роут возвращает все доступные мед карты")
    @GetMapping()
    public ResponseMessageAPI<List<MedCardDto>> getAll() {
        return new ResponseMessageAPI<>(
                service.getAll(),
                ResultCodeAPI.SUCCESS,
                null,
                "Все доступные мед карты успешно получены",
                ResultCode.OK.getHttpCode()
        );
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Мед карта по id успешно найдена.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Мед карта по этой id не найдена."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Не удалось выполнить поиск.")
    })
    @Operation(summary = "Этот роут для поиска мед карт по id.")
    @GetMapping("/{id}")
    public ResponseMessageAPI<MedCardDto> getById(@PathVariable Long id) {
        return new ResponseMessageAPI<>(
                service.getById(id),
                ResultCodeAPI.SUCCESS,
                null,
                "Мед карта по id успешно найдена.",
                ResultCode.OK.getHttpCode()
        );
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Мед карта успешно удалена.",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Мед карта по этой id не найдена."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Не удалось выполнить поиск.")
    })
    @Operation(summary = "Этот роут для удаления мед карт по id.")
    @PutMapping("/delete")
    public ResponseMessageAPI<String> delete(@RequestParam Long id) {
        return new ResponseMessageAPI<>(
                service.delete(id),
                ResultCodeAPI.SUCCESS,
                null,
                "Мед карта успешно удалена.",
                ResultCode.OK.getHttpCode()
        );
    }

}
