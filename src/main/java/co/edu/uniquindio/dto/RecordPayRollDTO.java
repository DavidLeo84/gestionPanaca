package co.edu.uniquindio.dto;

import co.edu.uniquindio.model.enums.Reason;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;


public record RecordPayRollDTO(

        @NotNull
        String dniEmployee,
        @NotNull
        int idUserEntity,
        @Positive
        Double price, // valor de la novedad
        @Positive
        float amountHours, // cantidad de horas extras
        @NotNull
        Reason typePayRollNew, //tipo de novedad
        @NotNull
        LocalDateTime  dateStart, // fecha de inicio de hora extra o creacion novedad
        LocalDateTime dateFinish, // fecha de fin hora extra
        @NotBlank
        @Lob
        String description // descripcion de la novedad

) {
}
