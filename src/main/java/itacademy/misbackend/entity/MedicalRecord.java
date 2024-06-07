package itacademy.misbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itacademy.misbackend.entity.helper.Diagnosis;
import itacademy.misbackend.entity.helper.Prescription;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "med_record_seq_generator")
    @SequenceGenerator(name = "med_record_seq_generator", sequenceName = "med_record_seq", allocationSize = 1)
    private Long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "med_card_id")
    private MedCard medCard;

    @OneToOne(fetch = FetchType.EAGER)
    private Diagnosis diagnosis;
    @OneToOne(fetch = FetchType.EAGER)
    private Prescription prescription;

    private String notes;
    private String recommendation;

    private String createdBy;
    private Timestamp createdAt;

    private String lastUpdatedBy;
    private Timestamp lastUpdatedAt;

    private String deletedBy;
    private Timestamp deletedAt;

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id=" + id +
                ", appointment=" + appointment +
                ", medCardID=" + medCard.getId() +
                ", diagnosis=" + diagnosis +
                ", prescription=" + prescription +
                ", notes='" + notes + '\'' +
                ", recommendation='" + recommendation + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", lastUpdatedAt=" + lastUpdatedAt +
                ", deletedBy='" + deletedBy + '\'' +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
