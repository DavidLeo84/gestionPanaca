package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record CreateUserDTO(

        @NotBlank
        @Size(max = 15)
        String username,
        @NotBlank
        String password,
        Set<String> roles
) {
}
