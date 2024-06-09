package itacademy.misbackend.exception;

import itacademy.misbackend.dto.CustomResponseMessage;
import itacademy.misbackend.enums.ResultCode;
import itacademy.misbackend.enums.ResultCodeAPI;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomResponseMessage<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        CustomResponseMessage<?> response = new CustomResponseMessage<>(
                null,
                ResultCodeAPI.FAIL,
                "Ошибка валидации",
                e.getBindingResult().getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining(", ")),
                ResultCode.FAIL
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomResponseMessage<?>> handleNotFoundException(NotFoundException e) {
        CustomResponseMessage<?> response = new CustomResponseMessage<>(
                null,
                ResultCodeAPI.FAIL,
                "Объект не найден",
                e.getMessage(),
                ResultCode.FAIL
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
