package itacademy.misbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecord {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    private String summary;
    private String notes;

    private String createdBy;
    private Timestamp createdAt;

    private String lastUpdatedBy;
    private Timestamp lastUpdatedAt;

    private String deletedBy;
    private Timestamp deletedAt;


}
