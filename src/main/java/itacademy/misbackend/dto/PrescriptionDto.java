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
public class PrescriptionDto {
    private Long id;

    private Long medicalRecordId;
    private String medication;
    private String dosage;
    private String instructions;

    private Timestamp prescriptionDate;
    private Timestamp lastUpdatedAt;
    private Timestamp deletedAt;
}
