package itacademy.misbackend.controller.auth;

import itacademy.misbackend.dto.CustomResponseMessage;
import itacademy.misbackend.dto.UserDoctorRequest;
import itacademy.misbackend.dto.UserPatientRequest;
import itacademy.misbackend.enums.ResultCode;
import itacademy.misbackend.enums.ResultCodeAPI;
import itacademy.misbackend.exception.NotFoundException;
import itacademy.misbackend.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/register")
public class RegistrationController {
    private final RegistrationService registrationService;
    @PostMapping("/doctor")
    public CustomResponseMessage<Void> registerDoctor(@Valid @RequestBody UserDoctorRequest userDoctorRequest) {
       try{
           registrationService.registerDoctor(userDoctorRequest);
           return new CustomResponseMessage(
                   null,
                   ResultCodeAPI.CREATED,
                   "Ожидайте сообщение для подтверждения регистрации на почту, которую вы указали при регистрации",
                   null,
                   ResultCode.CREATED);
       } catch (NotFoundException e) {
           return new CustomResponseMessage(
                   null,
                   ResultCodeAPI.FAIL,
                   "Ошибка",
                   e.getMessage(),
                   ResultCode.NOT_FOUND
           );
       } catch (Exception e) {
           return new CustomResponseMessage(
                   null,
                   ResultCodeAPI.EXCEPTION,
                   "Ошибка на стороне сервера",
                   e.getMessage(),
                   ResultCode.INTERNAL_SERVER_ERROR
           );
       }
    }

    @PostMapping("/patient")
    public CustomResponseMessage<Void> registerPatient(@Valid @RequestBody UserPatientRequest userPatientRequest) {
        try{
            registrationService.registerPatient(userPatientRequest);
            return new CustomResponseMessage(
                    null,
                    ResultCodeAPI.CREATED,
                    "Ожидайте сообщение для подтверждения регистрации на почту, которую вы указали при регистрации",
                    null,
                    ResultCode.CREATED);
        } catch (NotFoundException e) {
            return new CustomResponseMessage(
                    null,
                    ResultCodeAPI.FAIL,
                    "Ошибка",
                    e.getMessage(),
                    ResultCode.NOT_FOUND
            );
        } catch (Exception e) {
            return new CustomResponseMessage(
                    null,
                    ResultCodeAPI.EXCEPTION,
                    "Ошибка на стороне сервера",
                    e.getMessage(),
                    ResultCode.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/confirm")
    public CustomResponseMessage<Void> confirm(@RequestParam String token, @RequestParam Long id) {
        try{
            registrationService.confirm(token, id);
            return new CustomResponseMessage(
                    null,
                    ResultCodeAPI.SUCCESS,
                    "Регистрация успешно подтверждена",
                    null,
                    ResultCode.OK);
        } catch (NotFoundException e) {
            return new CustomResponseMessage(
                    null,
                    ResultCodeAPI.FAIL,
                    "Ошибка",
                    e.getMessage(),
                    ResultCode.NOT_FOUND
            );
        } catch (Exception e) {
            return new CustomResponseMessage(
                    null,
                    ResultCodeAPI.EXCEPTION,
                    "Ошибка на стороне сервера",
                    e.getMessage(),
                    ResultCode.INTERNAL_SERVER_ERROR
            );
        }
    }
}
