package co.edu.uniquindio.dto;

import co.edu.uniquindio.model.enums.EnumPay;
import jakarta.validation.constraints.NotBlank;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketDTO(

        @NotBlank
        String dni,

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotBlank
        String hireDate,

        @NotBlank
        String email,

        @NotBlank
        String appointment

) {
}
