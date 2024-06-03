package itacademy.misbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiagnosisDto {
    private Long id;

    //  private MedicalRecord medicalRecord;
    private String description;
    private String code;

    private Timestamp diagnosisDate;
    private Timestamp lastUpdatedAt;
    private Timestamp deletedAt;
}
