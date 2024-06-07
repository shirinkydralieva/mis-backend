package itacademy.misbackend.entity;

import itacademy.misbackend.entity.helper.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_seq_generator")
    @SequenceGenerator(name = "service_seq_generator", sequenceName = "service_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String description;
    private Long price;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private Timestamp deletedAt;

    @Override
    public String toString() {
        return "ServiceType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", department=" + department.getName() + "(id=" + department.getId() +
                "), deletedAt=" + deletedAt +
                '}';
    }
}
