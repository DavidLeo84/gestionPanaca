package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotBlank;

public record ItemEmployeeDTO(

        @NotBlank
        String firstName,
        @NotBlank
        String last_name,
        @NotBlank
        String appointment
) {
}
