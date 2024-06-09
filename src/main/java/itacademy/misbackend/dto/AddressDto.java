package itacademy.misbackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;

    @NotBlank(message = "Поле «Страна» не может быть пустым")
    private String country;

    private String region;

    private String district;

    @NotBlank(message = "Поле «Город/Населенный пункт» не может быть пустым")
    private String city;

    private String street;

    private String buildingNumber;
}
