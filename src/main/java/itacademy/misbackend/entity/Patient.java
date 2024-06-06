package itacademy.misbackend.entity;

import itacademy.misbackend.entity.helper.Address;
import itacademy.misbackend.entity.helper.Passport;
import itacademy.misbackend.enums.Sex;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_seq_generator")
    @SequenceGenerator(name = "patient_seq_generator", sequenceName = "patient_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @OneToOne(fetch = FetchType.EAGER)
    private Passport passport;

    @Column(unique = true, nullable = false)
    private String taxId;

    @OneToOne(fetch = FetchType.EAGER)
    private Address address;

    private String placeOfWork;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

    private LocalDateTime deletedAt;
    private String deletedBy;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private MedCard medCard;

}
