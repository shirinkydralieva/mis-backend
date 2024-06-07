package itacademy.misbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_request_seq_generator")
    @SequenceGenerator(name = "appointment_request_seq_generator", sequenceName = "appointment_request_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    private LocalDateTime appointmentDate;
}
