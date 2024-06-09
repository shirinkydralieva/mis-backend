package itacademy.misbackend.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStaffRequest {
    @Valid
    private UserDto userDto;

    @Valid
    private StaffDto staffDto;
}
