package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemDepartmentDTO(

        @NotNull
        int id,
        @NotBlank
        String departmentName
) {
}
