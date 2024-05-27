package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record Forms(

        @NotBlank
        String dni,

        @NotBlank
        String firstName,

        @NotBlank
        String lastname,

        @NotNull
        int idDepartment,

        @NotBlank
        String appointment,

        @NotBlank
        LocalDate hireDate

) {
}
