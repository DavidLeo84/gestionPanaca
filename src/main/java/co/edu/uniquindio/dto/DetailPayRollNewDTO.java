package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record DetailPayRollNewDTO(
        @NotNull
        String dniEmployee,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotNull
        LocalDateTime dateStart, // fecha de inicio de hora extra o creacion novedad
        @Positive
        Double price, // valor de la novedad
        @Positive
        float amountHours // cantidad de horas extras
) {
}
