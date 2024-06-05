package itacademy.misbackend.entity.helper;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq_generator")
    @SequenceGenerator(name = "address_seq_generator", sequenceName = "address_seq", allocationSize = 1)
    private Long id;
    private String country;
    private String region;//область
    private String district;
    private String city; //населенный пункт или город
    private String street;
    private String buildingNumber; //номер дома или квартиры

    //(По идее должно сохраниться вместе с пациентом)
}
