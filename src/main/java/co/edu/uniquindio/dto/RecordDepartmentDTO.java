package co.edu.uniquindio.dto;

import co.edu.uniquindio.model.UserEntity;
import lombok.NonNull;

public record RecordDepartmentDTO(

        @NonNull
        String departmentName

) {
}
