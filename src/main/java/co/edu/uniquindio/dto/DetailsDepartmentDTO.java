package co.edu.uniquindio.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record DetailsDepartmentDTO(

        @NotNull
        int id,
        @NotBlank
        String departmenteName,
        @NotNull
        String userEntityName,
        Set<String> employeesList
) {
}
