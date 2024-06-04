package itacademy.misbackend.entity;

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
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_seq_generator")
    @SequenceGenerator(name = "doctor_seq_generator", sequenceName = "doctor_seq", allocationSize = 1)
    private Long id;
    private String fullName; //фио
    private String specialization; //специальность врача
    private String qualification;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department; //отделение
    private String phoneNumber;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    private LocalDateTime deletedAt;
    private String deletedBy;
}
