package itacademy.misbackend.dto;

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
    private String firstName;
    private String lastName;
    private String patronymic; //Отчество
    private String specialization;
    private String qualification;
    private Long departmentId;
    private String phoneNumber;
    private Long userId;
    private List<AppointmentDto> appointments;
}
