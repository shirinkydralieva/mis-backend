package itacademy.misbackend.entity.helper;

import itacademy.misbackend.enums.PassportSeries;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"series", "number"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passport_seq_generator")
    @SequenceGenerator(name = "passport_seq_generator", sequenceName = "passport_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PassportSeries series;

    @Column(length = 7, nullable = false)
    private String number;
}
