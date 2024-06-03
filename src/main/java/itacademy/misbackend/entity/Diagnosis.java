package itacademy.misbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diagnosis_seq_generator")
    @SequenceGenerator(name = "diagnosis_seq_generator", sequenceName = "diagnosis_seq", allocationSize = 1)
    private Long id;

    //  private MedicalRecord medicalRecord;

    private String description;
    private String code;

    private Timestamp diagnosisDate;
    private Timestamp lastUpdatedAt;
    private Timestamp deletedAt;

}