package itacademy.misbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDto {
    private Long id;

    @NotBlank(message = "Поле «Имя» не может быть пустым")
    @NotNull
    private String firstName;

    @NotBlank(message = "Поле «Фамилия» не может быть пустым")
    @NotNull
    private String lastName;

    private String patronymic;

    private String specialization;
    private Long departmentId;

    private Long userId;
    private List<AppointmentDto> appointments;
}
