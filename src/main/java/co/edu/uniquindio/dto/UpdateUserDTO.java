package co.edu.uniquindio.dto;

import co.edu.uniquindio.model.Address;
import co.edu.uniquindio.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UpdateUserDTO(

        @NotNull
        int id,
        @NotBlank
        @Size(max = 20)
        String firstName,
        @NotBlank
        @Size(max = 20)
        String lastname,
        @NotBlank
        @Size(max = 12)
        String dni,
        @NotBlank
        @Size(max = 10)
        String numberPhone,
        @Size(max = 10)
        String localNumberPhone,
        @NotEmpty
        Address address,
        @NotBlank
        String birthDate
) {
}
