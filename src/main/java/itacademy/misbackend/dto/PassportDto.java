package itacademy.misbackend.dto;

import itacademy.misbackend.enums.PassportSeries;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassportDto {
    private Long id;

    @NotNull
    private PassportSeries series;

    @Pattern(regexp = "^[0-9]{7}$", message = "Номер паспорта должен содержать 7 цифр")
    @NotNull
    private String number;
}
