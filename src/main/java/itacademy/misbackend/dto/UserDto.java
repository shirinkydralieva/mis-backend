package itacademy.misbackend.dto;

import itacademy.misbackend.entity.helper.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;

    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_.]{7,29}$",
            message = "Имя пользователя должно начинаться с буквы " +
            "и содержать только буквы, цифры, символ подчеркивания, точку и быть длиной от 8 до 30 символов")
    @NotNull(message = "Поле «Имя пользователя» не может быть пустым")
    private String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "Пароль должен содержать хотя бы одну цифру, одну строчную и " +
                      "заглавную буквы, один спецсимвол и быть длиной от 8 до 20 символов")
    @NotNull(message = "Поле «Пароль» не может быть пустым")
    private String password;

    @Email(message = "Некорректный адрес электронной почты")
    @NotNull(message = "Поле «Электронная почта» не может быть пустым")
    private String email;

    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$",
            message = "Номер телефона должен содержать от 7 до 25 символов " +
                      "и может содержать только цифры, пробелы, скобки, дефисы и плюсы")
    @NotNull(message = "Поле «Номер телефона» не может быть пустым")
    private String phoneNumber;

    private boolean blocked;
    private Set<Role> roles;
}
