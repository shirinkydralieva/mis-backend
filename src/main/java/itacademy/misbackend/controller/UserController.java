package itacademy.misbackend.controller;

import itacademy.misbackend.dto.*;
import itacademy.misbackend.enums.ResultCode;
import itacademy.misbackend.enums.ResultCodeAPI;
import itacademy.misbackend.exception.NotFoundException;
import itacademy.misbackend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping()
    public CustomResponseMessage<UserDto> save(@Valid @RequestBody UserDto userDto){
        try{
            return new CustomResponseMessage<>(
                    userService.save(userDto),
                    ResultCodeAPI.SUCCESS,
                    "Пользователь успешно создан",
                    null,
                    ResultCode.CREATED
            );
        } catch (Exception e){
            return new CustomResponseMessage<>(
                    null,
                    ResultCodeAPI.EXCEPTION,
                    "Ошибка",
                    e.getMessage(),
                    ResultCode.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/new-doctor")
    public CustomResponseMessage<UserDoctorRequest> createDoctorUser(@Valid @RequestBody UserDoctorRequest userDoctor){
        try{
            return new CustomResponseMessage<>(
                    userService.createDoctor(userDoctor),
                    ResultCodeAPI.SUCCESS,
                    "Пользователь(Доктор) успешно создан",
                    null,
                    ResultCode.CREATED
            );
        } catch (Exception e){
            return new CustomResponseMessage<>(
                    null,
                    ResultCodeAPI.EXCEPTION,
                    "Ошибка",
                    e.getMessage(),
                    ResultCode.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/new-patient")
    public CustomResponseMessage<UserPatientRequest> createPatientUser(@Valid @RequestBody UserPatientRequest userPatient){
        try{
            return new CustomResponseMessage<>(
                    userService.createPatient(userPatient),
                    ResultCodeAPI.SUCCESS,
                    "Пользователь(Пациент) успешно создан",
                    null,
                    ResultCode.CREATED
            );
        } catch (Exception e){
            return new CustomResponseMessage<>(
                    null,
                    ResultCodeAPI.EXCEPTION,
                    "Ошибка",
                    e.getMessage(),
                    ResultCode.INTERNAL_SERVER_ERROR
            );
        }
    }
    @GetMapping("/{id}")
    public CustomResponseMessage<UserDto> getById(@PathVariable Long id){
        try {
            return new CustomResponseMessage<>(
                    userService.getById(id),
                    ResultCodeAPI.SUCCESS,
                    "Пользователь найден",
                    null,
                    ResultCode.OK
            );
        } catch (NotFoundException e){
            return new CustomResponseMessage<>(
                    null,
                    ResultCodeAPI.FAIL,
                    "Пользователь не найден",
                    e.getMessage(),
                    ResultCode.NOT_FOUND
            );
        } catch (Exception e) {
            return new CustomResponseMessage<>(
                    null,
                    ResultCodeAPI.EXCEPTION,
                    "Ошибка",
                    e.getMessage(),
                    ResultCode.INTERNAL_SERVER_ERROR
            );
        }

    }

    @GetMapping()
    public CustomResponseMessage<List<UserDto>> getAll(){
        try {
            return new CustomResponseMessage<>(
                    userService.getAll(),
                    ResultCodeAPI.SUCCESS,
                    "Список пользователей найден",
                    null,
                    ResultCode.OK
            );
        } catch (NotFoundException e){
            return new CustomResponseMessage<>(
                    null,
                    ResultCodeAPI.FAIL,
                    "Список пользователей пуст",
                    e.getMessage(),
                    ResultCode.NOT_FOUND
            );
        } catch (Exception e) {
            return new CustomResponseMessage<>(
                    null,
                    ResultCodeAPI.EXCEPTION,
                    "Ошибка",
                    e.getMessage(),
                    ResultCode.INTERNAL_SERVER_ERROR
            );
        }

    }

    @PutMapping("/{id}")
    public CustomResponseMessage<UserDto> update (@PathVariable Long id, @RequestBody UpdatedUser userDto) {
        try{
            return new CustomResponseMessage<>(
                    userService.update(id, userDto),
                    ResultCodeAPI.SUCCESS,
                    "Пользователь успешно обновлен",
                    null,
                    ResultCode.OK
            );
        } catch (NotFoundException e){
            return new CustomResponseMessage<>(
                    null,
                    ResultCodeAPI.FAIL,
                    "Пользователь не найден",
                    e.getMessage(),
                    ResultCode.NOT_FOUND
            );
        } catch (Exception e){
            return new CustomResponseMessage<>(
                    null,
                    ResultCodeAPI.EXCEPTION,
                    "Ошибка",
                    e.getMessage(),
                    ResultCode.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("/{id}")
    public CustomResponseMessage<String> delete (@PathVariable Long id) {
        try{
            return new CustomResponseMessage<>(
                    userService.delete(id),
                    ResultCodeAPI.SUCCESS,
                    "Пользователь успешно удален",
                    null,
                    ResultCode.OK
            );
        } catch (NotFoundException e){
            return new CustomResponseMessage<>(
                    null,
                    ResultCodeAPI.FAIL,
                    "Пользователь не найден",
                    e.getMessage(),
                    ResultCode.NOT_FOUND
            );
        } catch (Exception e){
            return new CustomResponseMessage<>(
                    null,
                    ResultCodeAPI.EXCEPTION,
                    "Ошибка",
                    e.getMessage(),
                    ResultCode.INTERNAL_SERVER_ERROR
            );
        }
    }
}
