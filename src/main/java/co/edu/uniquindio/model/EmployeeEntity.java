package co.edu.uniquindio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
    private Department department;

    private String appointment; //cargo que desempeña

    @ManyToOne
    private UserEntity userEntity;

    @OneToOne
    private Document document;

    @OneToOne(mappedBy = "employeeEntity")
    private NewEntity newEntity;

    @Builder
    public EmployeeEntity(String firstName, String lastname, String dni, String numberPhone,
                          String localNumberPhone, Address address, LocalDate birthDate,
                          String email, Department department, String appointment) {
        super(firstName, lastname, dni, numberPhone, localNumberPhone, address, birthDate);
        this.email = email;
        this.department = department;
        this.appointment = appointment;
    }

}
