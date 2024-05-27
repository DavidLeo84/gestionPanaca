package co.edu.uniquindio.dto;

import co.edu.uniquindio.model.enums.Reason;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UpdatePayRollDTO(

        @NotNull
        Reason typePayRollNew, //tipo de novedad
        @NotNull
        int idPayRollNew,
        @NotNull
        String dniEmployee,
        @Positive
        Double price, // valor de la novedad
        @Positive
        float amountHours, // cantidad de horas extras
        @NotNull
        LocalDateTime dateStart, // fecha de inicio de hora extra o creacion novedad

        LocalDateTime dateFinish, // fecha de fin hora extra
        @NotBlank
        @Lob
        String description // descripcion de la novedad
) {
}
