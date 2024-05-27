package co.edu.uniquindio.dto;

import co.edu.uniquindio.model.Address;
import co.edu.uniquindio.model.Department;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateEmployeeDTO(

        @NotBlank
        String dni,

        @NotBlank
        String firstName,

        @NotBlank
        String lastname,

        @NotBlank
        @Size(max = 10)
        String numberPhone,

        @NotBlank
        @Size(max = 10)
        String localNumberPhone,

        Address address


) {
}
