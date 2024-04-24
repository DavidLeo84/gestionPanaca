package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(

        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
