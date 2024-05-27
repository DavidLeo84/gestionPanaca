package co.edu.uniquindio.dto;

import co.edu.uniquindio.model.Address;
import co.edu.uniquindio.model.Department;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RecordEmployeeDTO(

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

        Address address,

        @NotBlank
        LocalDate birthDate,

        @Email
        String email,

        @NotNull
        int idDepartment,
        @NotBlank
        String appointment,

        @NotBlank
        LocalDate hireDate

) {
}
