package itacademy.misbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itacademy.misbackend.entity.helper.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private String firstName;
    private String lastName;
    private String patronymic; //Отчество
    private String specialization; //специальность врача
    private String qualification;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department; //отделение
    private String phoneNumber;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    private LocalDateTime deletedAt;
    private String deletedBy;

    @Override
    public String toString() {
        return "Doctor [id=" + id
                + ", department=" + department.getName()
                + ", appointments=" + appointments.stream().map(Appointment::getId).toList()
                + "]";
    }
}
