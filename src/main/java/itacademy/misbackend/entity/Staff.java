package itacademy.misbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staff_seq_generator")
    @SequenceGenerator(name = "staff_seq_generator", sequenceName = "staff_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    @OneToOne
    private User user;
    private String position;

}
