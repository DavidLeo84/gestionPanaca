package co.edu.uniquindio.model;

import co.edu.uniquindio.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.io.Serializable;
import java.util.List;


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
    @Column(nullable = false, length = 30, unique = true)
    private String email;

    @ManyToOne()
    private Department department; // departamento donde desempeña la labor

    private String appointment; //cargo que desempeña

    @Column(name = "hire_date")
    private String hireDate; // fecha de contratacion

//    @ManyToOne
//    private UserEntity userEntity; // usuario

    @OneToOne
    private Document document; // documentos pdf

    @OneToMany(mappedBy = "employeeEntity")
    private List<PayRollNew> payRollNewList;

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
