package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ItemUserEntityDTO(

        @NotNull
        int id,

        @NotBlank
        @Size(max = 10)
        String username
) {
}
