package itacademy.misbackend.dto;

import itacademy.misbackend.enums.Sex;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDto {
    private Long id;

    @NotBlank(message = "Поле «Имя» не может быть пустым")
    @NotNull
    private String firstName;

    @NotBlank(message = "Поле «Фамилия» не может быть пустым")
    @NotNull
    private String lastName;

    private String patronymic;

    @NotNull(message = "Поле «Пол» не может быть пустым")
    private Sex sex;

    @NotNull(message = "Поле «Дата рождения» не может быть пустым")
    private LocalDate dateOfBirth;

    @Valid
    private PassportDto passport;

    @Pattern(regexp = "^[0-9]{14}$", message = "ИНН должен содержать 14 цифр")
    private String taxId;

    @Valid
    @NotNull(message = "Поле «Адрес» не может быть пустым")
    private AddressDto address;

    private String placeOfWork;

    private Long userId;

    private List<AppointmentDto> appointments;

    private LocalDate deletedAt;
    private String deletedBy;
}
