package itacademy.misbackend.dto;


import itacademy.misbackend.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedCardDto {
    private Long id;
    private Patient patient;
    private List<MedicalRecordDto> medicalRecords;

    private String lastUpdatedBy;
    private LocalDateTime lastUpdatedAt;

}
