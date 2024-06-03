package itacademy.misbackend.dto;

import itacademy.misbackend.entity.MedicalRecord;
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

    private MedicalRecord medicalRecord;

    private String medication;
    private String dosage;
    private String instructions;

    private Timestamp prescriptionDate;
    private Timestamp lastUpdatedAt;
    private Timestamp deletedAt;
}
