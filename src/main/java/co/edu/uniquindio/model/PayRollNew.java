package co.edu.uniquindio.model;
import co.edu.uniquindio.model.enums.Reason;
import co.edu.uniquindio.model.enums.StatusPayRollNew;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pay_roll news")
public class PayRollNew implements Serializable { // clase para novedades de nomina de horas extras

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Reason typePayRollNew; //tipo de novedad

    private Double price; // valor de la novedad

    private String day; // dia de las horas extras

    private LocalDateTime dateStart; // fecha de inicio de hora extra o creacion novedad

    private LocalDateTime dateFinish; // fecha de fin hora extra

    private float amountHours; // cantidad de horas extras

    private String description; // descripcion de la novedad

    @Enumerated(EnumType.STRING)
    private StatusPayRollNew statusPayRollNew; // estado de la novedad (aprobado, rechazado, en proceso)

    @ManyToOne
    private EmployeeEntity employeeEntity;

    @ManyToOne
    private UserEntity userEntity;

    @Builder
    public PayRollNew(Reason typePayRollNew, Double price, String day, LocalDateTime dateStart,
                      LocalDateTime dateFinish, float amountHours, String description,
                      StatusPayRollNew statusPayRollNew, EmployeeEntity employeeEntity, UserEntity userEntity) {
        this.typePayRollNew = typePayRollNew;
        this.price = price;
        this.day = day;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.amountHours = amountHours;
        this.description = description;
        this.statusPayRollNew = statusPayRollNew;
        this.employeeEntity = employeeEntity;
        this.userEntity = userEntity;
    }
}
