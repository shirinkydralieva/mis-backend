package itacademy.misbackend.dto;

import itacademy.misbackend.enums.Gender;
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
    private String fullName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private PassportDto passport;
    private String taxId;
    private AddressDto address;
    private String phoneNumber;
    private String placeOfWork;
    private Long userId;
    private List<AppointmentDto> appointments;

    private LocalDate deletedAt;
    private String deletedBy;
}
