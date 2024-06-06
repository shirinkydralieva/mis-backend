package itacademy.misbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
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
    private Timestamp lastUpdatedAt;

    private String deletedBy;
    private Timestamp deletedAt;

}
