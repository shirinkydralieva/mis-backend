package itacademy.misbackend.entity.helper;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prescription_seq_generator")
    @SequenceGenerator(name = "prescription_seq_generator", sequenceName = "prescription_seq", allocationSize = 1)
    private Long id;

    private String medication;
    private String dosage;
    private String instructions;

    private LocalDateTime deletedAt;

}
