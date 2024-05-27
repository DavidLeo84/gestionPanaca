package co.edu.uniquindio.dto;

import co.edu.uniquindio.model.UserEntity;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

public record UpdateDepartmentDTO(
        @NotNull
        int idDepartment,

        @NonNull
        String departmentName

) {
}
