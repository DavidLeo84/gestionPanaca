package co.edu.uniquindio.model;

import co.edu.uniquindio.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@Entity
@ToString
//@Audited
@NoArgsConstructor
@Table(name = "employees")
public class EmployeeEntity extends Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Email
    @NotBlank
    @Size(max = 30)
    private String email;

    @ManyToOne()
    private Department department; // departamento donde desempeña la labor

    private String appointment; //cargo que desempeña

    private String hireDate; // fecha de contratacion

    @ManyToOne
    private UserEntity userEntity; // usuario

    @OneToOne
    private Document document; // documentos pdf

    @OneToOne(mappedBy = "employeeEntity")
    private PayRollNews payRollNews;

    @Builder
    public EmployeeEntity(String firstName, String lastname, String dni, String numberPhone,
                          String localNumberPhone, Address address, String birthDate,
                          String email, Department department, String appointment, String hireDate,
                          Status status) {
        super(firstName, lastname, dni, numberPhone, localNumberPhone, address, birthDate, status);
        this.email = email;
        this.department = department;
        this.appointment = appointment;
        this.hireDate = hireDate;
    }

}
