package itacademy.misbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedCard {
    @Id
    private Long id;

    @JsonIgnore
    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private Patient patient;
    
    @JsonIgnore
    @OneToMany(mappedBy = "medCard", cascade = CascadeType.ALL)
    private List<MedicalRecord> medicalRecords;

    private String lastUpdatedBy;
    private LocalDateTime lastUpdatedAt;

    private String deletedBy;
    private LocalDateTime deletedAt;

    @Override
    public String toString() {
        return "MedCard{" +
                "id=" + id +
                ", patient=" + patient +
                ", medicalRecords=" + medicalRecords +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", lastUpdatedAt=" + lastUpdatedAt +
                ", deletedBy='" + deletedBy + '\'' +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
