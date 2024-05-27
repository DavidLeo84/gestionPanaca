package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotBlank;

public record TokenDTO(

        @NotBlank
        String token
) {
}
