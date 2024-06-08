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
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diagnosis_seq_generator")
    @SequenceGenerator(name = "diagnosis_seq_generator", sequenceName = "diagnosis_seq", allocationSize = 1)
    private Long id;

    private String description;
    private String code;

    private LocalDateTime deletedAt;

}