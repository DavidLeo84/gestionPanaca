package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ItemPayRollDTO(

        @NotNull
        LocalDateTime date,
        @NotNull
        String dni
) {
}
