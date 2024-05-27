package co.edu.uniquindio.dto;

import co.edu.uniquindio.model.enums.StatusPayRollNew;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReviewPayRolNewDTO(

        @NotNull
        int idPayRoll,
        @NotBlank
        StatusPayRollNew status,
        @Size(max = 50)
        String descriptionReview
) {
}
